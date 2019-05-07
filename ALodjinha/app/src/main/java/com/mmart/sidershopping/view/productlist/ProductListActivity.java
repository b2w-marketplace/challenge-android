package com.mmart.sidershopping.view.productlist;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.mmart.sidershopping.ItemClickListener;
import com.mmart.sidershopping.view.productdetail.ProductDetailActivity;
import com.mmart.sidershopping.R;
import com.mmart.sidershopping.model.entity.Product;

public class ProductListActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        Intent intent = getIntent();
        int key = getKey(intent);
        String name = getName(intent);

        setActionBar(name);
        setProductList(key);
    }

    private String getName(Intent intent) {
        return intent.getStringExtra("name");
    }

    private int getKey(Intent intent) {

        return intent.getIntExtra("key", 0);
    }

    private void setProductList(int key) {
        final ProductListViewModel productListViewModel = ViewModelProviders.of(this, new ProductListViewModelFactory(key)).get(ProductListViewModel.class);
        final ProductListAdapter productAdapter = new ProductListAdapter(this);

        RecyclerView bestSellerList = findViewById(R.id.product_list);
        LinearLayoutManager layoutManagerBestSeller = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
        bestSellerList.setLayoutManager(layoutManagerBestSeller);

        productAdapter.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(Product product) {
                Intent intent = new Intent(getBaseContext(), ProductDetailActivity.class);
                intent.putExtra("key", product.getId());

                startActivity(intent);
            }
        });

        productListViewModel.productPagedList.observe(this, new Observer<PagedList<Product>>() {
            @Override
            public void onChanged(@Nullable PagedList<Product> products) {
            productAdapter.submitList(products);
            }
        });

        bestSellerList.setAdapter(productAdapter);
    }

    private void setActionBar(String name) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(name);
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
