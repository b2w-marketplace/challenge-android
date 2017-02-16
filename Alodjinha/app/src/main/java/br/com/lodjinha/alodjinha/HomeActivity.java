package br.com.lodjinha.alodjinha;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import br.com.lodjinha.alodjinha.adapter.ListViewAdapterHome;
import br.com.lodjinha.alodjinha.adapter.ViewPagerAdapter;
import br.com.lodjinha.alodjinha.interfaceapi.IRecuperaCategoria;
import br.com.lodjinha.alodjinha.interfaceapi.IRecuperaImagemDownload;
import br.com.lodjinha.alodjinha.interfaceapi.IRecuperaImgBanner;
import br.com.lodjinha.alodjinha.interfaceapi.IRecuperaMaisVendidos;
import br.com.lodjinha.alodjinha.model.Categoria;
import br.com.lodjinha.alodjinha.model.Imagem;
import br.com.lodjinha.alodjinha.model.Produto;
import br.com.lodjinha.alodjinha.repository.api.DownloadImagemApi;
import br.com.lodjinha.alodjinha.repository.api.RecuperaCategoriaApi;
import br.com.lodjinha.alodjinha.repository.api.RecuperaImgBannerApi;
import br.com.lodjinha.alodjinha.repository.api.RecuperaMaisVendidosApi;
import me.relex.circleindicator.CircleIndicator;

public class HomeActivity extends AppCompatActivity implements IRecuperaImgBanner,IRecuperaCategoria, IRecuperaImagemDownload, IRecuperaMaisVendidos {

    private RecyclerView horizontalView;
    private ArrayList<String> horizontalList;
    private HorizontalAdapter horizontalAdapter;
    private ArrayList<Categoria> categorias = new ArrayList<>();

    private ViewPager viewPager;
    private PagerAdapter adapter;
    ArrayList<Bitmap> banners = null;
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

    ArrayList<Produto> produtos = new ArrayList<>();
    ListView listViewAdapter;
    private static ListViewAdapterHome listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);



        progresso = (RelativeLayout) findViewById(R.id.progressBarLayout);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
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

            banners = new ArrayList<Bitmap>();

            for (int i = 0; i < bannerArray.length(); i++) {

                url = new URL(bannerArray.getJSONObject(i).getString("urlImagem"));
                imgConvertBanner = BitmapFactory.decodeStream(url.openConnection().getInputStream());

                banners.add(imgConvertBanner);
            }

            adapter = new ViewPagerAdapter(HomeActivity.this, banners);
            viewPager.setAdapter(adapter);
            circleIndicator.setViewPager(viewPager);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
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


        if(imgs.getTipo().equals("Categoria")) {
            for (Categoria c : categorias) {
                c.setImgs(imgs.getBmps().get(count++));
            }

            horizontalAdapter = new HorizontalAdapter(categorias);

            LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false);
            horizontalView.setLayoutManager(horizontalLayoutManagaer);

            horizontalView.setAdapter(horizontalAdapter);
        } else if(imgs.getTipo().equals("MaisVendido")){
            count = 0;
            for(Produto p : produtos){
                p.setImgProduto(imgs.getBmps().get(count++));
            }
            listAdapter = new ListViewAdapterHome(produtos, getApplicationContext());

            listViewAdapter.setAdapter(listAdapter);

            progresso.setVisibility(View.INVISIBLE);

            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
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
                produto.setNome(produtoArray.getJSONObject(i).getString("nome"));
                produto.setDescricao(produtoArray.getJSONObject(i).getString("descricao"));
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

            holder.txtView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(HomeActivity.this,holder.txtView.getText().toString(),Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return horizontalList.size();
        }
    }


}
