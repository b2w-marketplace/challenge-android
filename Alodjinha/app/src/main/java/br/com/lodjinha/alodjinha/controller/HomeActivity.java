package br.com.lodjinha.alodjinha.controller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.StrictMode;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import br.com.lodjinha.alodjinha.R;
import br.com.lodjinha.alodjinha.adapter.ListViewAdapterHome;
import br.com.lodjinha.alodjinha.adapter.ViewPagerAdapter;
import br.com.lodjinha.alodjinha.interfaceapi.IRecuperaCategoria;
import br.com.lodjinha.alodjinha.interfaceapi.IRecuperaImagemDownload;
import br.com.lodjinha.alodjinha.interfaceapi.IRecuperaImgBanner;
import br.com.lodjinha.alodjinha.interfaceapi.IRecuperaMaisVendidos;
import br.com.lodjinha.alodjinha.model.Banner;
import br.com.lodjinha.alodjinha.model.Categoria;
import br.com.lodjinha.alodjinha.model.Imagem;
import br.com.lodjinha.alodjinha.model.Produto;
import br.com.lodjinha.alodjinha.repository.api.DownloadImagemApi;
import br.com.lodjinha.alodjinha.repository.api.RecuperaCategoriaApi;
import br.com.lodjinha.alodjinha.repository.api.RecuperaImgBannerApi;
import br.com.lodjinha.alodjinha.repository.api.RecuperaMaisVendidosApi;
import me.relex.circleindicator.CircleIndicator;

public class HomeActivity extends AppCompatActivity implements IRecuperaImgBanner,IRecuperaCategoria, IRecuperaImagemDownload, IRecuperaMaisVendidos, NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView horizontalView;
    private ArrayList<String> horizontalList;
    private HorizontalAdapter horizontalAdapter;
    private ArrayList<Categoria> categorias = new ArrayList<>();
    private ArrayList<Banner> banners = new ArrayList<>();

    private ViewPager viewPager;
    private PagerAdapter adapter;
    private static int paginaAtual = 0;
    private static int numeroDePaginas = 0;
    private CircleIndicator circleIndicator;
    Categoria categoria = null;
    Produto produto = null;
    DownloadImagemApi imagapi;
    RecuperaImgBannerApi apiBanner = new RecuperaImgBannerApi(this);
    RecuperaCategoriaApi apiCategoria = new RecuperaCategoriaApi(this);
    RecuperaMaisVendidosApi apiMaisVendidos = new RecuperaMaisVendidosApi(this);
    RelativeLayout progresso;
    RelativeLayout subLayoutHome;
    Banner banner;
    Typeface custom_font;

    ArrayList<Produto> produtos = new ArrayList<>();
    ListView listViewAdapter;
    private static ListViewAdapterHome listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarHome);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.drawable.logo_navbar);

        TextView tx = (TextView) findViewById(R.id.txtTituloHome);

        custom_font = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");

        tx.setTypeface(custom_font);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        subLayoutHome = (RelativeLayout) findViewById(R.id.subLayoutHome);

        progresso = (RelativeLayout) findViewById(R.id.progressBarLayout);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        progresso.setVisibility(View.VISIBLE);

        new Thread(new Runnable() {
            @Override
            public void run() {
                apiBanner.delegate = HomeActivity.this;
                apiBanner.execute();

                apiCategoria.delegate = HomeActivity.this;
                apiCategoria.execute();

                apiMaisVendidos.delegate = HomeActivity.this;
                apiMaisVendidos.execute();
            }
        }).start();


        listViewAdapter = (ListView) findViewById(R.id.listViewAdapterCustom);

        listViewAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Produto produto = produtos.get(position);

                Intent it = new Intent(HomeActivity.this, DescricaoProdutoActivity.class);

                String desc = String.valueOf(produto.getDescricao());

                it.putExtra("NomeProduto", produto.getNome());
                it.putExtra("DescProduto", desc);
                it.putExtra("PrecoDe", produto.getPrecoDe());
                it.putExtra("PrecoPor", produto.getPrecoPor());
                it.putExtra("ImgProduto", produto.getUrlImagem());
                it.putExtra("IdProduto", produto.getId());
                startActivity(it);

            }
        });

        horizontalView = (RecyclerView) findViewById(R.id.horizontalView);

        viewPager = (ViewPager) findViewById(R.id.pager);
        circleIndicator = (CircleIndicator) findViewById(R.id.indicator);

        viewPager = (ViewPager) findViewById(R.id.pager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                paginaAtual = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                    if(state==ViewPager.SCROLL_STATE_IDLE){
                        int paginacount = banners.size();
                        if(paginaAtual == 0){
                            viewPager.setCurrentItem(paginacount-1,false);
                        } else if(paginaAtual == paginacount-1){
                          viewPager.setCurrentItem(0,false);
                        }
                    }
            }
        });

        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
                if(paginaAtual==numeroDePaginas){
                    paginaAtual = 0;
                }
                viewPager.setCurrentItem(paginaAtual++,true);
            }
        };

        Timer swipe = new Timer();
        swipe.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },3000,3000);

    }



    @Override
    public void preencheBanner(String retorno) {

        try {

            JSONObject bannerObject = new JSONObject(retorno);
            JSONArray bannerArray = bannerObject.getJSONArray("data");

            URL url = null;
            Bitmap imgConvertBanner = null;
            final ArrayList<String> st = new ArrayList<>();;


            for (int i = 0; i < bannerArray.length(); i++) {
                banner = new Banner();
                st.add(bannerArray.getJSONObject(i).getString("urlImagem"));
                banner.setUrlImagem(bannerArray.getJSONObject(i).getString("urlImagem"));
                banners.add(banner);
            }

            Imagem img = new Imagem();
            img.setTipo("banner");
            imagapi = new DownloadImagemApi(st, img);
            imagapi.delegate = HomeActivity.this;
            imagapi.execute();


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void preencheCategoria(String retorno) {
        try {

            JSONObject categoriaObject = new JSONObject(retorno);
            JSONArray categoriaArray = categoriaObject.getJSONArray("data");

            URL url = null;
            Bitmap imgConvertBanner = null;
            final ArrayList<String> st = new ArrayList<>();;


            for (int i = 0; i < categoriaArray.length(); i++) {
                categoria = new Categoria();
                categoria.setId(categoriaArray.getJSONObject(i).getInt("id"));
                categoria.setDescricao(categoriaArray.getJSONObject(i).getString("descricao"));
                categoria.setUrlImagem(categoriaArray.getJSONObject(i).getString("urlImagem"));
                st.add(categoriaArray.getJSONObject(i).getString("urlImagem"));
                categorias.add(categoria);
            }

                    Imagem img = new Imagem();
                    img.setTipo("Categoria");
                    imagapi = new DownloadImagemApi(st, img);
                    imagapi.delegate = HomeActivity.this;
                    imagapi.execute();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void preencheImagenss(Imagem imgs) {
        int count = 0;
        switch (imgs.getTipo().toString()){
            case "Categoria":
                for (Categoria c : categorias) {
                    c.setImgs(imgs.getBmps().get(count++));
                }

                horizontalAdapter = new HorizontalAdapter(categorias);

                LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false);
                horizontalView.setLayoutManager(horizontalLayoutManagaer);

                horizontalView.setAdapter(horizontalAdapter);
                break;
            case "MaisVendido":
                count = 0;
                for(Produto p : produtos){
                    p.setImgProduto(imgs.getBmps().get(count++));
                }
                listAdapter = new ListViewAdapterHome(produtos, getApplicationContext());

                listViewAdapter.setAdapter(listAdapter);

                subLayoutHome.setVisibility(View.VISIBLE);
                progresso.setVisibility(View.INVISIBLE);
                //getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                break;
            case "banner":
                count = 0;
                for(Banner b : banners){
                    b.setImgs(imgs.getBmps().get(count++));
                }

                adapter = new ViewPagerAdapter(HomeActivity.this, banners);
                viewPager.setAdapter(adapter);
                circleIndicator.setViewPager(viewPager);
                break;
            default:
                break;
        }
    }

    @Override
    public void preencheMaisVendidos(String retorno) {
        try {

            JSONObject produtoObject = new JSONObject(retorno);
            JSONArray produtoArray = produtoObject.getJSONArray("data");

            URL url = null;
            Bitmap imgConvertBanner = null;
            final ArrayList<String> st = new ArrayList<>();;


            for (int i = 0; i < produtoArray.length(); i++) {
                produto = new Produto();
                produto.setId(produtoArray.getJSONObject(i).getInt("id"));
                produto.setNome(produtoArray.getJSONObject(i).getString("nome"));
                String descricao = produtoArray.getJSONObject(i).getString("descricao");

                Spanned formatHtmlDescricao = fromHtml(descricao);
                produto.setDescricao(formatHtmlDescricao);
                produto.setUrlImagem(produtoArray.getJSONObject(i).getString("urlImagem"));
                produto.setPrecoDe(produtoArray.getJSONObject(i).getDouble("precoDe"));
                produto.setPrecoPor(produtoArray.getJSONObject(i).getDouble("precoPor"));
                st.add(produtoArray.getJSONObject(i).getString("urlImagem"));
                produtos.add(produto);
            }

            Imagem img = new Imagem();
            img.setTipo("MaisVendido");
            imagapi = new DownloadImagemApi(st, img);
            imagapi.delegate = HomeActivity.this;
            imagapi.execute();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {

        private List<Categoria> horizontalList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView txtView;
            public ImageView imgHorizontal;

            public MyViewHolder(View view) {
                super(view);
                txtView = (TextView) view.findViewById(R.id.textView);
                imgHorizontal = (ImageView) view.findViewById(R.id.imgHorizontal);

            }
        }


        public HorizontalAdapter(List<Categoria> horizontalList) {
            this.horizontalList = horizontalList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_horizontal_recyclerview, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
          holder.txtView.setText(horizontalList.get(position).getDescricao());
          holder.imgHorizontal.setImageBitmap(horizontalList.get(position).getImgs());


            holder.imgHorizontal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(HomeActivity.this, CategoriaActivity.class);
                    it.putExtra("idCategoria", horizontalList.get(position).getId());
                    startActivity(it);
                }
            });


        }

        @Override
        public int getItemCount() {
            return horizontalList.size();
        }
    }

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html){
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html,Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
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

        if (id == R.id.sobre_menu) {
            Intent it = new Intent(this, SobreActivity.class);
            startActivity(it);
            overridePendingTransition(android.R.anim.slide_in_left,0);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
