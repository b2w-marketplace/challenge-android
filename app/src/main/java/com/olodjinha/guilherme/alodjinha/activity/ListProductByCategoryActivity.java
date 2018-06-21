package com.olodjinha.guilherme.alodjinha.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.olodjinha.guilherme.alodjinha.R;
import com.olodjinha.guilherme.alodjinha.adapter.ProductPaginationAdapter;
import com.olodjinha.guilherme.alodjinha.model.Category;
import com.olodjinha.guilherme.alodjinha.model.DataProduct;
import com.olodjinha.guilherme.alodjinha.model.Product;
import com.olodjinha.guilherme.alodjinha.rest.ApiClient;
import com.olodjinha.guilherme.alodjinha.rest.ApiInterface;
import com.olodjinha.guilherme.alodjinha.utils.PaginationAdapterCallback;
import com.olodjinha.guilherme.alodjinha.utils.PaginationScrollListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Guilherme on 20/06/2018.
 */

public class ListProductByCategoryActivity extends AppCompatActivity implements PaginationAdapterCallback {

    private static final String TAG = ListProductByCategoryActivity.class.getSimpleName();

    //region Views

    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    @BindView(R.id.recyclerViewProducts)
    RecyclerView recyclerViewProducts;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    //endregion

    //region Constant. Vars

    public static String BUNDLE_CATEGORY = "com.olodjinha.guilherme.olodjinha.activity.ListProductByCategoryActivity.BUNDLE_CATEGORY";
    private Category category;

    private DataProduct dataProducts;
    private ProductPaginationAdapter productCategoryAdapter;

    LinearLayoutManager productCategoryLayoutManager;

    private int offset = 0;
    private int limit = 10;
    private int limitTotal = 10;

    private static final int PAGE_START = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 75;
    private int currentPage = PAGE_START;
    int mProductId;

    //endregion Constant. Vars

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product_category);

        ButterKnife.bind(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            category = (Category) extras.get(BUNDLE_CATEGORY);
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(category.getDescricao());
        mProductId = category.getId();

        callProductCategoryAdapter();
    }


    private void callProductCategoryAdapter() {
        productCategoryAdapter = new ProductPaginationAdapter(this);
        productCategoryLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewProducts.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerViewProducts.setLayoutManager(productCategoryLayoutManager);
        recyclerViewProducts.setItemAnimator(new DefaultItemAnimator());
        recyclerViewProducts.setAdapter(productCategoryAdapter);

        recyclerViewProducts.addOnScrollListener(new PaginationScrollListener(productCategoryLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;

                if (offset == 0) {
                    offset = 11;
                    limit = offset + 10;
                } else if (offset > 0) {
                    offset = limit;
                    limit = limit + 10;
                }

                if (limit >= limitTotal) {
                    offset = limit;
                    limit = limitTotal;
                }
                loadNextPage();
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });


        loadFirstPage();

        (productCategoryAdapter).setOnItemClickListener(new ProductPaginationAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Product product = ProductPaginationAdapter.getInstance().productResults.get(position);
                Intent intent = new Intent(ListProductByCategoryActivity.this, DescriptionProductActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(DescriptionProductActivity.BUNDLE_PRODUCT, product);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return true;
    }

    @Override
    public void retryPageLoad() {
        loadNextPage();
    }

    private List<Product> fetchResults(Response<DataProduct> response) {
        DataProduct dataProducts = response.body();
        offset = dataProducts.getOffset();
        return dataProducts.getProduct();
    }

    private void loadFirstPage() {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<DataProduct> call = apiService.getProduct(offset, limitTotal, mProductId);

        call.enqueue(new Callback<DataProduct>() {
            @Override
            public void onResponse(Call<DataProduct> call, Response<DataProduct> response) {

                DataProduct dataProducts = response.body();
                limitTotal = dataProducts.getTotal();

                List<Product> results = fetchResults(response);
                progressBar.setVisibility(View.GONE);
                productCategoryAdapter.addAll(results);

                if (currentPage <= TOTAL_PAGES) productCategoryAdapter.addLoadingFooter();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<DataProduct> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void loadNextPage() {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<DataProduct> call = apiService.getProduct(offset, limit, mProductId);

        call.enqueue(new Callback<DataProduct>() {
            @Override
            public void onResponse(Call<DataProduct> call, Response<DataProduct> response) {
                productCategoryAdapter.removeLoadingFooter();
                isLoading = false;

                List<Product> results = fetchResults(response);
                productCategoryAdapter.addAll(results);

                if (currentPage != TOTAL_PAGES) productCategoryAdapter.addLoadingFooter();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<DataProduct> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
