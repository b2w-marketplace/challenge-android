package br.com.bsavoini.lodjinha.catalog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import br.com.bsavoini.lodjinha.R;
import br.com.bsavoini.lodjinha.adapters.ProductsAdapter;
import br.com.bsavoini.lodjinha.api.model.ProductModel;
import br.com.bsavoini.lodjinha.product.ProductActivity;
import br.com.bsavoini.lodjinha.callbacks.ProductClickCallback;

import java.util.List;

public class CatalogActivity extends AppCompatActivity implements CatalogContract.CatalogView,
        CatalogScrollListener.CatalogScrollingCallback,
        ProductClickCallback {

    private ProgressBar progressBar;
    private RecyclerView productsRecycler;
    private CatalogContract.CatalogPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        presenter = new CatalogPresenterImpl(this, new CatalogInteractorImpl());
        presenter.initViews();
        presenter.requestProducts();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void setTollbarTitleWithCategory() {
        String category = getIntent().getStringExtra("categoryName");
        setTitle(category.toLowerCase());
    }

    @Override
    public void initRecyclerView() {
        productsRecycler = findViewById(R.id.recyler_products);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        productsRecycler.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));
        productsRecycler.addItemDecoration(dividerItemDecoration);
        productsRecycler.addOnScrollListener(new CatalogScrollListener(this));
    }

    @Override
    public void onClickProduct(ProductModel productModel) {
        Intent intent = new Intent(this, ProductActivity.class);
        intent.putExtra("product", productModel);
        startActivity(intent);
    }

    @Override
    public void enableToolbarBackButton() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public int retrieveCategoryId() {
        return getIntent().getIntExtra("categoryId", 0);
    }

    @Override
    public void updateAdapter(List<ProductModel> productsArr) {
        ProductsAdapter adapter = (ProductsAdapter) productsRecycler.getAdapter();
        if (adapter == null) {
            productsRecycler.setAdapter(new ProductsAdapter(productsArr, this));
        } else {
            adapter.setPopularMoviesList(productsArr);
        }
    }

    @Override
    public void initErrorMessageView() {

    }

    @Override
    public void hideErrorMsg() {
        //todo hideErrorMsg
    }

    @Override
    public void showErrorMsg() {
        //todo showErrorMsg
    }

    @Override
    public void initProgressBar() {
        progressBar = findViewById(R.id.progress_bar);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onRecyclerScrolledBottom() {
        presenter.requestProducts();
    }
}
