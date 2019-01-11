package br.com.bsavoini.lodjinha;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import br.com.bsavoini.lodjinha.about.AboutFragment;
import br.com.bsavoini.lodjinha.home.HomeFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TextView txtTitle;
    private ImageView imgLogo;


    private final String HOME_TAG = "HOME";
    private final String ABOUT_TAG = "ABOUT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Typeface tf = Typeface.createFromAsset(getAssets(), "pacifico.ttf");

        txtTitle = findViewById(R.id.title);
        txtTitle.setTypeface(tf);

        imgLogo = findViewById(R.id.logo);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        View headerView = navigationView.getHeaderView(0);
        TextView txtLogo = headerView.findViewById(R.id.txt_logo);
        txtLogo.setTypeface(tf);

        showHomeContent();
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
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            showHomeContent();
        } else if (id == R.id.nav_about) {
            showAboutContent();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showHomeContent() {
        replaceContent(new HomeFragment(), HOME_TAG);

        txtTitle.setText(getString(R.string.app_name));
        imgLogo.setVisibility(View.VISIBLE);
    }

    private void showAboutContent() {
        replaceContent(new AboutFragment(), ABOUT_TAG);

        txtTitle.setText(getString(R.string.about));
        imgLogo.setVisibility(View.GONE);
    }

    private void replaceContent(Fragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager != null) {
            Fragment visibleFragment = fragmentManager.findFragmentByTag(tag);
            if (visibleFragment == null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, fragment, tag).commit();
            }
        }
    }
}
