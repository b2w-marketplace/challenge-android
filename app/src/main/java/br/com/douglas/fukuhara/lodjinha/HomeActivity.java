package br.com.douglas.fukuhara.lodjinha;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import br.com.douglas.fukuhara.lodjinha.view.AboutFragment;
import br.com.douglas.fukuhara.lodjinha.view.HomeFragment;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private TextView tvCustomToolbarTitle;

    private final int CURRENT_FRAGMENT = 0;
    private final String HOME_FRAGMENT = "home_fragment";
    private final String ABOUT_FRAGMENT = "about_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        initNavDrawerComponents();
        initFragmentOnTop(savedInstanceState);
    }

    private void initNavDrawerComponents() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = findViewById(R.id.home_drawer_layout);
        tvCustomToolbarTitle = findViewById(R.id.toolbar_custom_text);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.drawer_open_desc, R.string.drawer_close_desc);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.home_drawer_nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initFragmentOnTop(Bundle savedInstanceState) {
        if(savedInstanceState == null) {
            loadFragment(new HomeFragment(), HOME_FRAGMENT);
        } else {
            handleToolbarCustomization(getSupportFragmentManager().getFragments().get(CURRENT_FRAGMENT));
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        switch (id) {
            case R.id.home_drawer_menu_home:
                Fragment homeFragmentByTag = getSupportFragmentManager().findFragmentByTag(HOME_FRAGMENT);
                if (homeFragmentByTag == null || !homeFragmentByTag.isVisible()) {
                    loadFragment(new HomeFragment(), HOME_FRAGMENT);
                }
                break;
            case R.id.home_drawer_menu_about:
                Fragment aboutFragmentByTag = getSupportFragmentManager().findFragmentByTag(ABOUT_FRAGMENT);
                if (aboutFragmentByTag == null || !aboutFragmentByTag.isVisible()) {
                    loadFragment(new AboutFragment(), ABOUT_FRAGMENT);
                }
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void loadFragment(Fragment fragment, String tag) {
        handleToolbarCustomization(fragment);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment, tag).commit();
    }

    private void handleToolbarCustomization(Fragment fragment) {
        if (fragment instanceof AboutFragment) {
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setTitle(R.string.about_toolbar_title);
            getSupportActionBar().setIcon(null);
            tvCustomToolbarTitle.setVisibility(View.GONE);
        } else if (fragment instanceof HomeFragment) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setIcon(R.drawable.logo_navbar);
            tvCustomToolbarTitle.setVisibility(View.VISIBLE);
        }
    }
}