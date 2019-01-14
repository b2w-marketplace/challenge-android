package b2w.com.alodjinha;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import b2w.com.alodjinha.Adapter.BannerViewPagerAdapter;
import b2w.com.alodjinha.Adapter.CategoriesAdapter;
import b2w.com.alodjinha.Adapter.ProductsAdapter;
import b2w.com.alodjinha.Async.GetBannerUrlAsync;
import b2w.com.alodjinha.Async.GetCategoryUrlAsync;
import b2w.com.alodjinha.Async.GetProductUrlAsync;
import b2w.com.alodjinha.Interface.BannerAsyncResponse;
import b2w.com.alodjinha.Interface.CategoryAsyncResponse;
import b2w.com.alodjinha.Interface.ProductAsyncResponse;
import b2w.com.alodjinha.Model.Banner;
import b2w.com.alodjinha.Model.Category;
import b2w.com.alodjinha.Model.Product;
import b2w.com.alodjinha.View.WrapContentViewPager;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BannerAsyncResponse, CategoryAsyncResponse, ProductAsyncResponse {

    Toolbar toolbar;
    FloatingActionButton fab;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    WrapContentViewPager bannerViewPager;
    PagerAdapter adapter;
    LinearLayout sliderDotsPanel;
    private int dotsCount;
    private List<ImageView> dots2;

    GetBannerUrlAsync asyncTaskBanner = null;
    GetCategoryUrlAsync asyncTaskCategory = null;
    GetProductUrlAsync asyncTaskProduct = null;

    private RecyclerView recyclerViewCategory;

    private RecyclerView recyclerViewMostSoldProducts;


    List<Product> listProducts;
    List<Category> listCategories;

    Menu drawer_menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        LoadViews();

        GetBanners();
        GetCategories();
        GetMostSoldProducts();

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        ClearMenuCheck();

    }

    private void ClearMenuCheck(){
        if ( drawer_menu != null ){
            MenuItem menuItem = drawer_menu.findItem(R.id.nav_home);
            menuItem.setChecked(false);

            menuItem = drawer_menu.findItem(R.id.nav_tag);
            menuItem.setChecked(false);
        }
    }

    private void GetBanners() {

        String url = "https://alodjinha.herokuapp.com/banner";

        asyncTaskBanner = new GetBannerUrlAsync(this);
        asyncTaskBanner.delegate = this;
        asyncTaskBanner.execute(url);

    }

    private void GetCategories() {

        String url = "https://alodjinha.herokuapp.com/categoria";

        asyncTaskCategory = new GetCategoryUrlAsync(this);
        asyncTaskCategory.delegate = this;
        asyncTaskCategory.execute(url);

    }

    private void GetMostSoldProducts() {

        String url = "https://alodjinha.herokuapp.com/produto/maisvendidos";

        asyncTaskProduct = new GetProductUrlAsync(this);
        asyncTaskProduct.delegate = this;
        asyncTaskProduct.execute(url);

    }

    private void LoadViews() {

        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Pacifico-Regular.ttf");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if ( getSupportActionBar() != null )
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView appNameToolbar = (TextView) toolbar.findViewById(R.id.app_name_toolbar_text_view);
        appNameToolbar.setTypeface(type);



        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        drawer_menu = navigationView.getMenu();


        View headerView = navigationView.getHeaderView(0);
        TextView appName = (TextView) headerView.findViewById(R.id.app_name_text_view);

        appName.setTypeface(type);


        bannerViewPager = (WrapContentViewPager) findViewById(R.id.banners_view_pager);


        recyclerViewCategory = (RecyclerView) findViewById(R.id.categories_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategory.setLayoutManager( layoutManager );

        recyclerViewMostSoldProducts = (RecyclerView) findViewById(R.id.most_sold_products_recycler);
        RecyclerView.LayoutManager layoutManagerProducts = new LinearLayoutManager(this);
        recyclerViewMostSoldProducts.setLayoutManager( layoutManagerProducts );

    }






    @Override
    public void processFinishBanner(final List<Banner> banners) {

        adapter = new BannerViewPagerAdapter(Home.this, banners);
        bannerViewPager.setAdapter(adapter);
        ViewGroup.LayoutParams vpParams = bannerViewPager.getLayoutParams();
        vpParams.height = 150;
        bannerViewPager.setLayoutParams(vpParams);


        sliderDotsPanel = (LinearLayout) findViewById(R.id.dots_linear_layout);
        dotsCount = adapter.getCount();
        //sliderDotsPanel.bringToFront();
        //ViewCompat.setTranslationZ(sliderDotsPanel, 1);









        dots2 = new ArrayList<>();

        for(int i = 0; i < dotsCount; i++) {
            ImageView dot = new ImageView(this);
            if ( i == 0 ) dot.setImageDrawable(getResources().getDrawable(R.drawable.active_dot));
            else dot.setImageDrawable(getResources().getDrawable(R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(8, 5, 8, 5);
            sliderDotsPanel.addView(dot, params);

            dots2.add(dot);
        }

        bannerViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                selectDot(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 4000);

    }

    public void selectDot(int idx) {
        Resources res = getResources();
        for (int i = 0; i < dotsCount; i++) {
            int drawableId = (i == idx) ? (R.drawable.active_dot) : (R.drawable.nonactive_dot);
            Drawable drawable = res.getDrawable(drawableId);
            dots2.get(i).setImageDrawable(drawable);
        }
    }

    @Override
    public void processFinishCategory(final List<Category> categories){

        listCategories = categories;
        CategoriesAdapter categoriesAdapter = new CategoriesAdapter( this, listCategories );
        recyclerViewCategory.setAdapter(categoriesAdapter);

    }

    @Override
    public void processFinishProduct(final List<Product> products){

        listProducts = products;
        ProductsAdapter mostSoldAdapter = new ProductsAdapter( this, listProducts );
        recyclerViewMostSoldProducts.setAdapter(mostSoldAdapter);

    }



    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {

            Home.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    int position = bannerViewPager.getCurrentItem() + 1;
                    if ( position == adapter.getCount() ) position = 0;
                    bannerViewPager.setCurrentItem( position );

                }
            });

        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {


        } else if (id == R.id.nav_tag) {
            Intent intent = new Intent( Home.this, About.class );
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }
}
