package com.mmart.sidershopping.view.homepage;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.mmart.sidershopping.AboutActivity;
import com.mmart.sidershopping.ItemClickListener;
import com.mmart.sidershopping.ItemClickListenerCategory;
import com.mmart.sidershopping.R;
import com.mmart.sidershopping.model.entity.Banner;
import com.mmart.sidershopping.model.entity.Category;
import com.mmart.sidershopping.model.entity.Product;
import com.mmart.sidershopping.view.productdetail.ProductAdapter;
import com.mmart.sidershopping.view.productdetail.ProductDetailActivity;
import com.mmart.sidershopping.view.productlist.ProductListActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        final HomeViewModel homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        setBanner(homeViewModel);
        setCategories(homeViewModel);
        setBestSellers(homeViewModel);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_about) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setBanner(HomeViewModel homeViewModel) {

        homeViewModel.getBanners().observe(this, new Observer<List<Banner>>() {
            @Override
            public void onChanged(@Nullable List<Banner> banners) {

                final ViewPagerAdapter adapter = new ViewPagerAdapter(getBaseContext(), banners);

                ViewPager viewPager = findViewById(R.id.view_pager);
                TabLayout tabLayout = findViewById(R.id.tab_dots);
                tabLayout.setupWithViewPager(viewPager, true);
                viewPager.setAdapter(adapter);
            }
        });
    }

    private void setCategories(HomeViewModel homeViewModel) {

        final CategoryAdapter categoryAdapter = new CategoryAdapter();

        categoryAdapter.setOnItemClickListener(new ItemClickListenerCategory() {
            @Override
            public void onItemClick(Category category) {

                Intent intent = new Intent(getBaseContext(), ProductListActivity.class);
                intent.putExtra("key", category.getId());

                startActivity(intent);
            }
        });


        homeViewModel.getCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(@Nullable List<Category> categories) {
                RecyclerView categoryListView = findViewById(R.id.activity_main_category_list);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.HORIZONTAL, false);
                categoryListView.setLayoutManager(layoutManager);

                categoryAdapter.submitList(categories);
                categoryListView.setAdapter(categoryAdapter);
            }
        });


    }

    private void setBestSellers(HomeViewModel homeViewModel) {

        final ProductAdapter productAdapter = new ProductAdapter(getBaseContext());

        productAdapter.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(Product product) {
                Intent intent = new Intent(getBaseContext(), ProductDetailActivity.class);
                intent.putExtra("key", product.getId());

                startActivity(intent);
            }

        });

        homeViewModel.getBestSellers().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable List<Product> products) {
                RecyclerView bestSellerList = findViewById(R.id.activity_main_best_seller_list);
                LinearLayoutManager layoutManagerBestSeller = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
                bestSellerList.setLayoutManager(layoutManagerBestSeller);

                productAdapter.submitList(products);

                bestSellerList.setAdapter(productAdapter);
            }
        });
    }
}
