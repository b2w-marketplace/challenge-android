package br.com.kakobotasso.lodjinha;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import br.com.kakobotasso.lodjinha.utils.Fontes;
import br.com.kakobotasso.lodjinha.utils.Navegacao;

public class SobreActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        preparaToolbar();
        preparaNavigationDrawer();
        preparaNaviationView();
        personalizaTexto();
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
        setSupportActionBar(toolbar);
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

    private void personalizaTexto(){
        TextView titulo = (TextView) findViewById(R.id.titulo_sobre);
        Fontes.aplicaEstilo(this, titulo);
    }
}
