package com.abmm.b2w.alodjinha.activities.product_list;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.abmm.b2w.alodjinha.R;
import com.abmm.b2w.alodjinha.activities.BaseAppCompatActivity;
import com.abmm.b2w.alodjinha.adapters.ProductListAdapter;
import com.abmm.b2w.alodjinha.http_module.paging.OnLoadMoreListener;
import com.abmm.b2w.alodjinha.model.Category;
import com.abmm.b2w.alodjinha.utils.Constants.Keys;

import org.parceler.Parcels;

import butterknife.BindView;

public class ProductListActivity extends BaseAppCompatActivity implements ProductListPresenterImpl.IProductListView {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.products_list_recycler_view) RecyclerView mProductsRecyclerView;

    private IProductListPresenter presenter;
    private ProductListAdapter mAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_product_list;
    }

    @Override
    protected void initUi() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Category category = Parcels.unwrap(getIntent().getParcelableExtra(Keys.CATEGORY_DATA));

        this.presenter = new ProductListPresenterImpl(this);
        this.presenter.setCategory(category);

        setTitle(category.getDescription());

        mProductsRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mProductsRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new ProductListAdapter(presenter);
        mProductsRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener((OnLoadMoreListener) presenter);
    }

    @Override
    protected void makeRequests() {
        presenter.loadNextPage();
    }

    @Override
    public ProductListAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return this.mProductsRecyclerView;
    }

}
