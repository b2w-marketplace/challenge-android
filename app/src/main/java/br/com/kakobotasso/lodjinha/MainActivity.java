package br.com.kakobotasso.lodjinha;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import br.com.kakobotasso.lodjinha.adapters.BannersAdapter;
import br.com.kakobotasso.lodjinha.adapters.CategoriasAdapter;
import br.com.kakobotasso.lodjinha.api.BannerService;
import br.com.kakobotasso.lodjinha.api.CategoriaService;
import br.com.kakobotasso.lodjinha.models.Banner;
import br.com.kakobotasso.lodjinha.models.Categoria;
import br.com.kakobotasso.lodjinha.models.DataBannerContainer;
import br.com.kakobotasso.lodjinha.models.DataCategoriaContainer;
import br.com.kakobotasso.lodjinha.utils.Fontes;
import br.com.kakobotasso.lodjinha.utils.Navegacao;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preparaToolbar();
        preparaNavigationDrawer();
        preparaNaviationView();

        carregaBanners();
        carregaCategorias();
    }

    @Override
    public void onBackPressed() {
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

        switch (id){
            case R.id.nav_home:
                Navegacao.vaiParaHome(this);
                break;

            case R.id.nav_sobre:
                Navegacao.vaiParaSobre(this);
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void preparaToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        personalizaToolbar();
        setSupportActionBar(toolbar);
    }

    private void personalizaToolbar(){
        TextView titulo = (TextView) toolbar.findViewById(R.id.toolbar_titulo);
        Fontes.aplicaEstilo(this, titulo);
    }

    private void preparaNavigationDrawer(){
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    private void preparaNaviationView(){
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        personalizaNavigationView();
    }

    private void personalizaNavigationView(){
        View headerView =  navigationView.getHeaderView(0);
        TextView test = (TextView) headerView.findViewById(R.id.nav_titulo_menu);
        Fontes.aplicaEstilo(this, test);
    }

    private void carregaBanners(){
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.banners_list);

        BannerService bannerService = BannerService.retrofit.create(BannerService.class);
        Call<DataBannerContainer> call = bannerService.getBanners();
        call.enqueue(new Callback<DataBannerContainer>() {
            @Override
            public void onResponse(Call<DataBannerContainer> call, Response<DataBannerContainer> response) {
                List<Banner> bannerList = response.body().getDataModelList();
                BannersAdapter adapter = new BannersAdapter(bannerList, MainActivity.this);
                recyclerView.setAdapter(adapter);
                RecyclerView.LayoutManager layout = new LinearLayoutManager(MainActivity.this,
                        LinearLayoutManager.HORIZONTAL, false);

                recyclerView.setLayoutManager(layout);
            }

            @Override
            public void onFailure(Call<DataBannerContainer> call, Throwable t) {

            }
        });
    }

    private void carregaCategorias(){
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.categorias_list);

        CategoriaService categoriaService = CategoriaService.retrofit.create(CategoriaService.class);
        Call<DataCategoriaContainer> call = categoriaService.getCategorias();
        call.enqueue(new Callback<DataCategoriaContainer>() {
            @Override
            public void onResponse(Call<DataCategoriaContainer> call, Response<DataCategoriaContainer> response) {
                List<Categoria> categoriaList = response.body().getDataModelList();
                CategoriasAdapter adapter = new CategoriasAdapter(categoriaList, MainActivity.this);
                recyclerView.setAdapter(adapter);
                RecyclerView.LayoutManager layout = new LinearLayoutManager(MainActivity.this,
                        LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(layout);
            }

            @Override
            public void onFailure(Call<DataCategoriaContainer> call, Throwable t) {

            }
        });
    }
}
