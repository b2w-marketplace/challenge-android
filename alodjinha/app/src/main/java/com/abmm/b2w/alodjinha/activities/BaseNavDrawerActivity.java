package com.abmm.b2w.alodjinha.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;

import com.abmm.b2w.alodjinha.LodjinhaApp;
import com.abmm.b2w.alodjinha.R;
import com.abmm.b2w.alodjinha.activities.about.AboutActivity;
import com.abmm.b2w.alodjinha.activities.main.MainActivity;

import butterknife.BindView;

public abstract class BaseNavDrawerActivity extends BaseAppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void initUi() {
        setSupportActionBar(toolbar);

        setupTopbar();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
    }

    protected void setupTopbar(){}

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Class nextActivity;
        switch (item.getItemId()) {
            default:
            case R.id.nav_home:
                nextActivity = MainActivity.class;
                break;

            case R.id.nav_about:
                nextActivity = AboutActivity.class;
                break;
        }

        LodjinhaApp.getInstance().callActivity(nextActivity);

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
