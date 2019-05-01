package com.abmm.b2w.alodjinha.activities.product_list;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.abmm.b2w.alodjinha.R;
import com.abmm.b2w.alodjinha.activities.BaseAppCompatActivity;
import com.abmm.b2w.alodjinha.adapters.ProductListAdapter;
import com.abmm.b2w.alodjinha.http_module.Envelope;
import com.abmm.b2w.alodjinha.http_module.ILodjinhaApi;
import com.abmm.b2w.alodjinha.http_module.LodjinhaApiClient;
import com.abmm.b2w.alodjinha.http_module.paging.OnLoadMoreListener;
import com.abmm.b2w.alodjinha.http_module.paging.PagingDataManager;
import com.abmm.b2w.alodjinha.model.Category;
import com.abmm.b2w.alodjinha.model.Product;
import com.abmm.b2w.alodjinha.utils.Constants.Keys;

import org.parceler.Parcels;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListActivity extends BaseAppCompatActivity implements OnLoadMoreListener {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.products_list_recycler_view) RecyclerView mProductsRecyclerView;

    private Category mCategory;
    private ProductListAdapter mAdapter;

    private PagingDataManager listDataManager;
    private ILodjinhaApi api = LodjinhaApiClient.buildApiClient();
    private boolean forceReload = false;

    @Override
    protected int getLayout() {
        return R.layout.activity_product_list;
    }

    @Override
    protected void initUi() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listDataManager = PagingDataManager.getInstance();
        listDataManager.clearComponents();
        listDataManager.resetQueryParameters();

        mProductsRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mProductsRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new ProductListAdapter(listDataManager.getComponents(), mProductsRecyclerView);
        mProductsRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(this);
    }

    @Override
    protected void loadData() {
        mCategory = Parcels.unwrap(getIntent().getParcelableExtra(Keys.CATEGORY_DATA));

        setTitle(mCategory.getDescription());
    }

    @Override
    protected void makeRequests() {
        loadNextPage();
    }


    private Handler handler = new Handler();

    private void loadNextPage() {
        if (listDataManager.hasMoreData()) {
            Map<String, Integer> queryMap = new HashMap<>();

            queryMap.put("offset", listDataManager.getOffset());
            queryMap.put("limit", listDataManager.getLimit());
            queryMap.put("categoriaId", mCategory.getId());

            api.getProducts(queryMap).enqueue(handleProductsCallback());
        } else {
            removeLastItem();
        }
    }

    private Callback<Envelope<Product>> handleProductsCallback() {
        return new Callback<Envelope<Product>>() {
            @Override
            public void onResponse(@NonNull Call<Envelope<Product>> call, @NonNull Response<Envelope<Product>> response) {
                Envelope<Product> pagedResult = response.body();
                updateLoadedInfo(pagedResult);
            }

            @Override
            public void onFailure(@NonNull Call<Envelope<Product>> call, @NonNull Throwable t) {

            }
        };
    }

    @Override
    public void onLoadMore() {
        if (listDataManager.getComponents().size() <= listDataManager.getComponents().size() + 3) {
            mAdapter.setNewItem(null);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    loadNextPage();
                }
            });
        }
    }

    private void removeLastItem() {
        if (!listDataManager.getComponents().isEmpty() && listDataManager.getComponents().get(listDataManager.getComponents().size() - 1) == null) {
            mAdapter.removeLastItem();
        }
    }

    private void updateLoadedInfo(final Envelope<Product> pagedResult) {
        removeLastItem();
        listDataManager.appendNewItems(pagedResult.getData());
        mAdapter.setNewItems(forceReload);

        listDataManager.updateForNextPage(pagedResult.getData().size());
        forceReload = false;
    }

}
