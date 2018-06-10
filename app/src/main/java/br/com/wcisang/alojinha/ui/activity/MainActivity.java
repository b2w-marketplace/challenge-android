package br.com.wcisang.alojinha.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import br.com.wcisang.alojinha.R;
import br.com.wcisang.alojinha.ui.fragment.AboutFragment;
import br.com.wcisang.alojinha.ui.fragment.MainFragment;
import butterknife.BindView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Fragment currentFragment;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();
        setupNavDrawer();
        replaceFragmentContainer(new MainFragment());
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    protected void setupNavDrawer() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_item_home:
                replaceFragmentContainer(new MainFragment());
                break;
            case R.id.nav_item_sobre:
                replaceFragmentContainer(new AboutFragment());
                break;
        }
        return false;
    }

    private void replaceFragmentContainer(Fragment fragment){
        if (currentFragment != fragment){
            currentFragment = fragment;
            getSupportFragmentManager().
                    beginTransaction()
                    .replace(R.id.container, currentFragment)
                    .commit();
        }
        drawer.closeDrawers();
    }

}
