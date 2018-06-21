package com.olodjinha.guilherme.alodjinha.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.olodjinha.guilherme.alodjinha.R;
import com.olodjinha.guilherme.alodjinha.adapter.BestSellersAdapter;
import com.olodjinha.guilherme.alodjinha.adapter.CategoryAdapter;
import com.olodjinha.guilherme.alodjinha.adapter.FragmentAdapter;
import com.olodjinha.guilherme.alodjinha.model.Category;
import com.olodjinha.guilherme.alodjinha.model.Data;
import com.olodjinha.guilherme.alodjinha.model.DataCategory;
import com.olodjinha.guilherme.alodjinha.model.DataProduct;
import com.olodjinha.guilherme.alodjinha.model.Product;
import com.olodjinha.guilherme.alodjinha.rest.ApiClient;
import com.olodjinha.guilherme.alodjinha.rest.ApiInterface;
import com.olodjinha.guilherme.alodjinha.utils.PaginationAdapterCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    //region Views

    @BindView(R.id.recyclerViewCategory)
    RecyclerView recyclerViewCategory;

    @BindView(R.id.recyclerViewBestSellers)
    RecyclerView recyclerViewBestSellers;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.textViewTitleAbout)
    TextView textViewTitleAbout;

    @BindView(R.id.contentMain)
    View contentMain;

    @BindView(R.id.relativeLayoutAbout)
    RelativeLayout relativeLayoutAbout;

    @BindView(R.id.indicators)
    LinearLayout indicator;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    //endregions

    //region Constant. Vars

    ActionBarDrawerToggle toggle;

    private Data dataBanners;
    private DataCategory dataCategorys;
    private DataProduct dataBestSellers;

    private int mDotCount;

    private LinearLayout[] mDots;

    private FragmentAdapter fragmentAdapter;
    private CategoryAdapter categoryAdapter;
    private BestSellersAdapter bestSellersAdapter;

    private RecyclerView.LayoutManager bestSellersLayoutManager;

    //endregion Constant. Vars

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setupToolbar();

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        callApiLoadBanners();
        callApiLoadCategorys();
        callApiLoadBestSellers();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            toolbar.setTitleTextAppearance(this, R.style.PacificoTextAppearance);
        }
        getSupportActionBar().setLogo(R.drawable.logo_navbar);
    }

    private void setupToolbarSobre() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.about);
        getSupportActionBar().setLogo(null);
    }

    private void callApiLoadBanners() {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Data> call = apiService.getDataBanner();

        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                dataBanners = response.body();
                if (dataBanners != null) {
                    callBannerAdapter(dataBanners);
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    private void callApiLoadCategorys() {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<DataCategory> call = apiService.getCategory();

        call.enqueue(new Callback<DataCategory>() {
            @Override
            public void onResponse(Call<DataCategory> call, Response<DataCategory> response) {
                dataCategorys = response.body();
                if (dataCategorys != null) {
                    callCategoryAdapter(dataCategorys);
                }
            }

            @Override
            public void onFailure(Call<DataCategory> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    private void callApiLoadBestSellers() {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<DataProduct> call = apiService.getBestSellers();

        call.enqueue(new Callback<DataProduct>() {
            @Override
            public void onResponse(Call<DataProduct> call, Response<DataProduct> response) {
                dataBestSellers = response.body();
                if (dataBestSellers != null) {
                    callBestSellersAdapter(dataBestSellers);
                }
            }

            @Override
            public void onFailure(Call<DataProduct> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    private void callBannerAdapter(Data listDataBanner) {

        fragmentAdapter = new FragmentAdapter(this, getSupportFragmentManager(), listDataBanner.getBanner());
        viewPager.setAdapter(fragmentAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(this);
        setUiPageViewBannerController();
    }

    private void callCategoryAdapter(DataCategory listCategory) {
        categoryAdapter = new CategoryAdapter(this, listCategory.getCategory());
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategory.setLayoutManager(horizontalLayoutManagaer);
        recyclerViewCategory.setItemAnimator(new DefaultItemAnimator());
        recyclerViewCategory.setAdapter(categoryAdapter);

        (categoryAdapter).setOnItemClickListener(new CategoryAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Category category = CategoryAdapter.getInstance().categoryList.get(position);
                Intent intent = new Intent(MainActivity.this, ListProductByCategoryActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(ListProductByCategoryActivity.BUNDLE_CATEGORY, category);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void callBestSellersAdapter(DataProduct listProduct) {
        bestSellersAdapter = new BestSellersAdapter(this, listProduct.getProduct());
        bestSellersLayoutManager = new LinearLayoutManager(this);
        recyclerViewBestSellers.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerViewBestSellers.setLayoutManager(bestSellersLayoutManager);
        recyclerViewBestSellers.setItemAnimator(new DefaultItemAnimator());
        recyclerViewBestSellers.setAdapter(bestSellersAdapter);

        (bestSellersAdapter).setOnItemClickListener(new BestSellersAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Product product = BestSellersAdapter.getInstance().productList.get(position);
                Intent intent = new Intent(MainActivity.this, DescriptionProductActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(DescriptionProductActivity.BUNDLE_PRODUCT, product);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void setUiPageViewBannerController() {

        mDotCount = fragmentAdapter.getCount();
        mDots = new LinearLayout[mDotCount];

        for (int i = 0; i < mDotCount; i++) {
            mDots[i] = new LinearLayout(this);
            mDots[i].setBackgroundResource(R.drawable.nonselected_item);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);
            indicator.addView(mDots[i], params);
            mDots[0].setBackgroundResource(R.drawable.selected_item);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < mDotCount; i++) {
            mDots[i].setBackgroundResource(R.drawable.nonselected_item);
        }
        mDots[position].setBackgroundResource(R.drawable.selected_item);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @OnClick(R.id.nav_home_menu)
    public void navHomeMenu() {
        setupToolbar();
        contentMain.setVisibility(View.VISIBLE);
        relativeLayoutAbout.setVisibility(View.GONE);
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    @OnClick(R.id.nav_about_menu)
    public void navAboutMenu() {
        setupToolbarSobre();
        relativeLayoutAbout.setVisibility(View.VISIBLE);
        contentMain.setVisibility(View.GONE);
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}