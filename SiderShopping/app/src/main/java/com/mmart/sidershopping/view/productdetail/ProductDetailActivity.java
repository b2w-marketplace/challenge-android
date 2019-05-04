package com.mmart.sidershopping.view.productdetail;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mmart.sidershopping.R;
import com.mmart.sidershopping.model.entity.Product;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ProductDetailActivity extends AppCompatActivity {

    private static DecimalFormat df2 = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        setActionBar();

        final int key = getKey();

        setProduct(key);
    }

    private void setProduct(final int key) {
        final ProductDetailViewModel productDetailViewModel = ViewModelProviders.of(this).get(ProductDetailViewModel.class);

        productDetailViewModel.getProduct(key).observe(this, new Observer<Product>() {
            @Override
            public void onChanged(@Nullable Product product) {

                ImageView image = findViewById(R.id.product_image);
                TextView name = findViewById(R.id.product_name);
                TextView oldPrice = findViewById(R.id.product_old_price);
                TextView newPrice = findViewById(R.id.product_new_price);
                TextView description = findViewById(R.id.product_description);

                Picasso.get()
                        .load(product.getUrlImagem())
                        .fit()
                        .centerCrop()
                        .into(image);

                name.setText(product.getNome());
                description.setText(Html.fromHtml(product.getDescricao()));
                oldPrice.setText("De: " + df2.format(product.getPrecoDe()));
                newPrice.setText("Por " + df2.format(product.getPrecoPor()));
            }
        });

        FloatingActionButton button = findViewById(R.id.floating_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reserve(key, productDetailViewModel);

                finish();
            }
        });
    }

    private void reserve(int key, ProductDetailViewModel productDetailViewModel) {
        productDetailViewModel.reserve(key).observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String message) {
                Toast.makeText(ProductDetailActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int getKey() {
        Intent intent = getIntent();
        return intent.getIntExtra("key", 0);
    }

    private void setActionBar() {
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}