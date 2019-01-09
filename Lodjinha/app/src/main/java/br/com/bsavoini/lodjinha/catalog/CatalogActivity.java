package br.com.bsavoini.lodjinha.catalog;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import br.com.bsavoini.lodjinha.R;
import br.com.bsavoini.lodjinha.api.RetrofitInstance;
import br.com.bsavoini.lodjinha.api.model.ProductModel;
import br.com.bsavoini.lodjinha.api.model.ProductsResponse;
import br.com.bsavoini.lodjinha.product.ProductActivity;
import br.com.bsavoini.lodjinha.product.ProductClickCallback;
import br.com.bsavoini.lodjinha.product.ProductsAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatalogActivity extends AppCompatActivity implements ProductClickCallback {
    private RecyclerView productsRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        showToolbarBackButton();


        String category = getIntent().getStringExtra("categoryName");
        setTitle(category.toLowerCase());

        productsRecycler = findViewById(R.id.recyler_products);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        productsRecycler.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));
        productsRecycler.addItemDecoration(dividerItemDecoration);

        int categoryId = getIntent().getIntExtra("categoryId", 0);
        requestProducts(categoryId);

    }

    //TODO call request from interactor
    private void requestProducts(int categoryId) {
        Call<ProductsResponse> productsResponseCall =
                RetrofitInstance.getLodjinhaService().requestProducts(0, 20, categoryId);

        productsResponseCall.enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {

                productsRecycler.setAdapter(new ProductsAdapter(response.body().getProductsArr(), CatalogActivity.this));
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void showToolbarBackButton() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public void onClickProduct(ProductModel productModel) {
        Intent intent = new Intent(this, ProductActivity.class);
        intent.putExtra("product", productModel);
        startActivity(intent);
    }
}
