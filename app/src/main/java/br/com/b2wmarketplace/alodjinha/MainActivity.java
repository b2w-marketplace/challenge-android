package br.com.b2wmarketplace.alodjinha;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.facebook.drawee.backends.pipeline.Fresco;

import br.com.b2wmarketplace.alodjinha.fragments.HomeFragment;
import br.com.b2wmarketplace.alodjinha.fragments.CategoriaProdutoFragment;
import br.com.b2wmarketplace.alodjinha.fragments.DetalheProdutoFragment;
import br.com.b2wmarketplace.alodjinha.fragments.SobreFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Fragment fragmentHome, fragmentCategoriaProduto;
    private FragmentManager fM = getSupportFragmentManager();
    private Fragment fragment = null;

    public boolean categoriaproduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);


        inicializa();

        if (savedInstanceState == null) {
            fragmentHome = HomeFragment.newInstance();
            fM.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fM.beginTransaction().add(R.id.FrameLayout, fragmentHome, "home").commit();
        }

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            fragment = null;
            if (fM.findFragmentByTag("sobre") != null){
                fragment = getSupportFragmentManager().findFragmentByTag("sobre");
            }else if (fM.findFragmentByTag("produtodetalhe") != null) {
                fragment = getSupportFragmentManager().findFragmentByTag("produtodetalhe");
            }else if (fM.findFragmentByTag("categoriaproduto") != null) {
                fragment = getSupportFragmentManager().findFragmentByTag("categoriaproduto");
            }
            if (fragment == null) {
                super.onBackPressed();
            } else
            if (fM.getBackStackEntryCount() > 0) {
                fM.popBackStack();
                if (fragment instanceof SobreFragment || fragment instanceof CategoriaProdutoFragment || !categoriaproduto) {
                    fM.beginTransaction().show(fragmentHome).commit();
                }else {
                    fM.beginTransaction().show(fragmentCategoriaProduto).commit();
                    categoriaproduto = false;
                }

            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnuHome:
                abreFragment(0);
                break;
            case R.id.mnuSobre:
                abreFragment(1);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void inicializa() {
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
    }

    public void setMenu(Toolbar toolbar) throws NullPointerException {
        navigationView.setItemIconTintList(null);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    public void abreFragment(int posicao) {
        if (posicao == 0) {
            if (fM.findFragmentByTag("sobre") != null) {
                fM.popBackStack();
            }
            fM.beginTransaction().show(fragmentHome).commit();
        } else {
            fragment = SobreFragment.newInstance();
            if (fM.findFragmentByTag("sobre") != null) {
                fM.beginTransaction().show(fragment).commit();
            } else {
                fM.beginTransaction().hide(fragmentHome).commit();
                fM.beginTransaction().add(R.id.FrameLayout, fragment, "sobre").addToBackStack(null).commit();
            }
        }
    }

    public void abreCategoriaProduto(int id, String descricao){
        fragmentCategoriaProduto = CategoriaProdutoFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putString("descricao", descricao);
        fragmentCategoriaProduto.setArguments(bundle);
        fM.beginTransaction().hide(fragmentHome).commit();
        fM.beginTransaction().add(R.id.FrameLayout, fragmentCategoriaProduto, "categoriaproduto").addToBackStack(null).commit();
    }

    public void abreDetalheProduto(int id, boolean categoriaproduto){
        fragment = DetalheProdutoFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        fragment.setArguments(bundle);
        if (categoriaproduto){
            fM.beginTransaction().hide(fragmentCategoriaProduto).commit();
        }else {
            fM.beginTransaction().hide(fragmentHome).commit();
        }
        fM.beginTransaction().add(R.id.FrameLayout, fragment, "produtodetalhe").addToBackStack(null).commit();
        this.categoriaproduto = categoriaproduto;
    }

    public void callBackPressed() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                onBackPressed();
            }
        });
    }
}
