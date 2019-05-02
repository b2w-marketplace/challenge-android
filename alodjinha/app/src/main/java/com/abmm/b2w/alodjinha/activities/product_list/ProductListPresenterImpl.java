package com.abmm.b2w.alodjinha.activities.product_list;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.abmm.b2w.alodjinha.adapters.ProductListAdapter;
import com.abmm.b2w.alodjinha.http_module.Envelope;
import com.abmm.b2w.alodjinha.http_module.ILodjinhaApi;
import com.abmm.b2w.alodjinha.http_module.LodjinhaApiClient;
import com.abmm.b2w.alodjinha.http_module.paging.OnLoadMoreListener;
import com.abmm.b2w.alodjinha.http_module.paging.PagingDataManager;
import com.abmm.b2w.alodjinha.model.Category;
import com.abmm.b2w.alodjinha.model.Product;
import com.abmm.b2w.alodjinha.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListPresenterImpl implements IProductListPresenter, OnLoadMoreListener {

    private IProductListView mView;

    private Category mCategory;
    private PagingDataManager listDataManager;
    private boolean forceReload = false;

    private final ILodjinhaApi api = LodjinhaApiClient.buildApiClient();

    ProductListPresenterImpl(IProductListView productListView) {
        this.mView = productListView;
        this.listDataManager = PagingDataManager.getInstance();
        resetParameters();
    }

    @Override
    public void setCategory(Category category) {
        this.mCategory = category;
    }

    @Override
    public List<Product> getProductList() {
        return listDataManager.getComponents();
    }

    @Override
    public void loadNextPage() {
        if (listDataManager.hasMoreData()) {
            int offset = listDataManager.getOffset();
            int limit = listDataManager.getLimit();

            api.getProducts(offset, limit, mCategory.getId()).enqueue(handleProductsCallback());
        } else {
            removeLastItem();
        }
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mView.getRecyclerView();
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

    private void removeLastItem() {
        if (!listDataManager.getComponents().isEmpty() && listDataManager.getComponents().get(listDataManager.getComponents().size() - 1) == null) {
            mView.getAdapter().removeLastItem();
        }
    }

    private void updateLoadedInfo(final Envelope<Product> pagedResult) {
        removeLastItem();
        listDataManager.appendNewItems(pagedResult.getData());
        mView.getAdapter().setNewItems(forceReload);

        listDataManager.updateForNextPage(pagedResult.getData().size());
        forceReload = false;
    }

    @Override
    public void onLoadMore() {
        if (listDataManager.getComponents().size() <= listDataManager.getComponents().size() + Constants.Paging.DEFAULT_PAGE_SIZE) {
            mView.getAdapter().setNewItem(null);
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    loadNextPage();
                }
            });
        }
    }

    private void resetParameters() {
        listDataManager.clearComponents();
        listDataManager.resetQueryParameters();
    }

    interface IProductListView {

        ProductListAdapter getAdapter();

        RecyclerView getRecyclerView();
    }
}
