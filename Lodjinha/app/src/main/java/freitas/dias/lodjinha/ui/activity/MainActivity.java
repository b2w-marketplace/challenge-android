package freitas.dias.lodjinha.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import freitas.dias.lodjinha.R;
import freitas.dias.lodjinha.ui.fragment.AboutFragment;
import freitas.dias.lodjinha.ui.fragment.HomeFragment;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private LinearLayout linearLayoutToolbar;
    private LinearLayout linearLayoutToolbarAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        setupDrawerLayout();
        openHomeFragment();
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == android.support.v7.appcompat.R.id.home) {
            return mDrawerToggle.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            int count = getSupportFragmentManager().getBackStackEntryCount();

            if(count == 1){
                setTitleToolbar(true);
            }
            if (count > 1){
                getSupportFragmentManager().popBackStack();
            }else {
                super.onBackPressed();
            }
        }
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        linearLayoutToolbar = (LinearLayout) toolbar.findViewById(R.id.toolbar_linear_home);
        linearLayoutToolbarAbout = (LinearLayout) toolbar.findViewById(R.id.toolbar_linear_home_about);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            getSupportActionBar().setTitle(null);
        }
    }

    private void setupDrawerLayout() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);

        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        initDrawerItem(item);
        drawerLayout.closeDrawers();
        return true;
    }

    private void initDrawerItem(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.drawer_item_work:
                openFragmentAbout();
                break;
            case R.id.drawer_home:
                openHomeFragment();
                break;
        }
    }

    private void setTitleToolbar(boolean home) {
        ActionBar toolbar = getSupportActionBar();
        if (toolbar != null) {
            if (home) {
                getSupportActionBar().setDisplayShowTitleEnabled(false);
                getSupportActionBar().setTitle(null);
                linearLayoutToolbar.setVisibility(View.VISIBLE);
                linearLayoutToolbarAbout.setVisibility(View.GONE);
            } else {
                linearLayoutToolbar.setVisibility(View.GONE);
                linearLayoutToolbarAbout.setVisibility(View.VISIBLE);
            }
        }
    }

    private void openHomeFragment(){
        setTitleToolbar(true);

        HomeFragment homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(HomeFragment.TAG);
        if (homeFragment == null) {
            replaceFragment(R.id.fragment_container, new HomeFragment(), HomeFragment.TAG, HomeFragment.TAG);
        }
    }

    private void openFragmentAbout(){
        AboutFragment aboutFragment = (AboutFragment) getSupportFragmentManager().findFragmentByTag(AboutFragment.TAG);
        setTitleToolbar(false);
        if (aboutFragment == null) {
            replaceFragment(R.id.fragment_container, new AboutFragment(), AboutFragment.TAG, AboutFragment.TAG);
        }
    }
}
