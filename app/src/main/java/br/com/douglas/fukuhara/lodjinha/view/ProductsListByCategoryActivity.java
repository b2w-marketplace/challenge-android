package br.com.douglas.fukuhara.lodjinha.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import br.com.douglas.fukuhara.lodjinha.R;
import br.com.douglas.fukuhara.lodjinha.adapter.ProductsListAdapter;
import br.com.douglas.fukuhara.lodjinha.interfaces.ProductsByListCategoryContract;
import br.com.douglas.fukuhara.lodjinha.network.RetrofitImpl;
import br.com.douglas.fukuhara.lodjinha.network.vo.ProductDataVo;
import br.com.douglas.fukuhara.lodjinha.presenter.ProductsListByCategoryPresenter;

public class ProductsListByCategoryActivity extends AppCompatActivity
    implements ProductsByListCategoryContract.View {

    private static final String CATEGORY_ID_BUNDLE_KEY = "category_id_bundle_key";
    private static final String CATEGORY_NAME_BUNDLE_KEY = "category_name_bundle_key";
    private final Integer DEFAULT_CATEGORY_ID_VALUE = 0;
    private final String DEFAULT_CATEGORY_NAME_VALUE = "";

    private ProductsByListCategoryContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private ProductsListAdapter mAdapter;

    public static Intent newIntent(Context context, int categoryId, String categoryName) {
        Intent intent = new Intent(context, ProductsListByCategoryActivity.class);
        intent.putExtra(CATEGORY_ID_BUNDLE_KEY, categoryId);
        intent.putExtra(CATEGORY_NAME_BUNDLE_KEY, categoryName);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list_by_category);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mProgressBar = findViewById(R.id.pb_loading_more_products);

        setUpRecyclerView();

        Bundle bundle = getIntent().getExtras();

        Integer categoryId = DEFAULT_CATEGORY_ID_VALUE;
        String categoryName = DEFAULT_CATEGORY_NAME_VALUE;
        if (bundle != null) {
            if (bundle.containsKey(CATEGORY_ID_BUNDLE_KEY)) {
                categoryId = bundle.getInt(CATEGORY_ID_BUNDLE_KEY, DEFAULT_CATEGORY_ID_VALUE);
            }
            if (bundle.containsKey(CATEGORY_NAME_BUNDLE_KEY)) {
                categoryName = bundle.getString(CATEGORY_NAME_BUNDLE_KEY, DEFAULT_CATEGORY_NAME_VALUE);

                if (!categoryName.isEmpty() && !CATEGORY_NAME_BUNDLE_KEY.equals(categoryName)) {
                    getSupportActionBar().setTitle(categoryName);
                }
            }
        }

        mPresenter = new ProductsListByCategoryPresenter(this,
                RetrofitImpl.getInstance(), categoryId);

        mPresenter.fetchData();
    }

    private void setUpRecyclerView() {
        mRecyclerView = findViewById(R.id.rv_list_of_products_by_category);
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL));
        mAdapter = new ProductsListAdapter(null, this::onProductItemClick);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    mPresenter.fetchData();
                }
            }
        });
    }

    @Override
    public void displayListOfProducts(List<ProductDataVo> listOfProducts) {
        mAdapter.updateDataSetContent(listOfProducts);
    }

    private void onProductItemClick(ProductDataVo productDataVo) {
        startActivity(ProductDetailActivity.newIntent(this, productDataVo));
    }

    @Override
    public void showLoader() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissLoader() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showRecyclerViewContainer() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onProductListFailedGenericError() {

    }

    @Override
    public void onProductFetchFailed(String message) {

    }

    @Override
    protected void onDestroy() {
        mPresenter.disposeAll();
        super.onDestroy();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
