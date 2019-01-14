package b2w.com.br.olodjinha;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import b2w.com.br.olodjinha.injection.NetworkModule;
import b2w.com.br.olodjinha.ui.about.AboutFragment;
import b2w.com.br.olodjinha.ui.home.HomeFragment;
import b2w.com.br.olodjinha.injection.DaggerMainComponent;
import b2w.com.br.olodjinha.injection.MainComponent;
import b2w.com.br.olodjinha.injection.ScreenFlowModule;
import b2w.com.br.olodjinha.util.screenflow.FragmentFlowManager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.nav_view)
    NavigationView mNavigationView;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.appbar_imageview)
    ImageView mAppbarImage;

    @BindView(R.id.appbar_textview)
    TextView mAppbarText;

    @Inject
    FragmentFlowManager mFragmentFlowManager;

    @Inject
    MainComponent mInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mInjector = DaggerMainComponent
                .builder()
                .screenFlowModule(new ScreenFlowModule())
                .build();
        mInjector.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        mFragmentFlowManager.showFragment(HomeFragment.getInstance(), this);

        initDrawer();
    }

    public void setAppBar(Fragment fragment) {
        if (fragment instanceof HomeFragment) {
            mAppbarImage.setVisibility(View.VISIBLE);
            mAppbarText.setText(R.string.home);
            mAppbarText.setTextAppearance(this, R.style.font_pacifico_regular);
        } else {
            mAppbarImage.setVisibility(View.GONE);
            mAppbarText.setText(R.string.about);
            mAppbarText.setTextAppearance(this, R.style.font_roboto_medium);
        }
    }

    private void initDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);
        mNavigationView.setItemIconTintList(null);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            mFragmentFlowManager.showFragment(HomeFragment.getInstance(), this);
        } else if (id == R.id.nav_about) {
            mFragmentFlowManager.showFragment(AboutFragment.getInstance(), this);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
