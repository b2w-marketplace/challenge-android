package br.com.soulblighter.lodjinha.ui.main;

import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import br.com.soulblighter.lodjinha.R;
import br.com.soulblighter.lodjinha.databinding.MainActivityBinding;
import br.com.soulblighter.lodjinha.ui.main.about.AboutFragment;
import br.com.soulblighter.lodjinha.ui.main.home.HomeFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private MainActivityBinding mBinding;
    private HomeFragment mHomeFragment;
    private AboutFragment mAboutFragment;
    private int mSelectedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.main_activity);

        setSupportActionBar(mBinding.appBarMain.toolbar);

        //getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setDisplayUseLogoEnabled(true);
        //getSupportActionBar().setLogo(R.drawable.logo_menu);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                mBinding.drawerLayout,
                mBinding.appBarMain.toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mBinding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mBinding.navView.setNavigationItemSelectedListener(this);
        mBinding.navView.setItemIconTintList(null);
        mSelectedFragment = R.id.nav_home;
        setFirstItemNavigationView(mSelectedFragment);

        Typeface pacificoRegularFont = Typeface.createFromAsset(getAssets(), "fonts/pacifico_regular.ttf");
        TextView navTitle = mBinding.navView.getHeaderView(0).findViewById(R.id.nav_title);
        navTitle.setTypeface(pacificoRegularFont);

        mBinding.appBarMain.toolbarText.setTypeface(pacificoRegularFont);
    }

    @Override
    public void onBackPressed() {
        if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mBinding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setFirstItemNavigationView(int menuId) {
        mBinding.navView.setCheckedItem(menuId);
        mBinding.navView.getMenu().performIdentifierAction(menuId, 0);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.nav_about:
                if (mAboutFragment == null) {
                    try {
                        mAboutFragment = AboutFragment.newInstance();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                fragment = mAboutFragment;
                break;

            case R.id.nav_home:
            default:
                if (mHomeFragment == null) {
                    try {
                        mHomeFragment = HomeFragment.newInstance();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                fragment = mHomeFragment;
        }

        if (fragment != null) {
            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_content,
                    fragment).commit();
            // Set action bar title
            setTitle(item.getTitle());
        }

        mBinding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
