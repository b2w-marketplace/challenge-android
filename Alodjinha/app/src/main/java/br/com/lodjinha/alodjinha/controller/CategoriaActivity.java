package br.com.lodjinha.alodjinha.controller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

import br.com.lodjinha.alodjinha.R;
import br.com.lodjinha.alodjinha.adapter.ListViewAdapterCategoria;
import br.com.lodjinha.alodjinha.interfaceapi.IRecuperaImagemDownload;
import br.com.lodjinha.alodjinha.interfaceapi.IRecuperaProdutoCategoria;
import br.com.lodjinha.alodjinha.model.Imagem;
import br.com.lodjinha.alodjinha.model.Produto;
import br.com.lodjinha.alodjinha.repository.api.DownloadImagemApi;
import br.com.lodjinha.alodjinha.repository.api.RecuperaProdutoCategoriaApi;

public class CategoriaActivity extends AppCompatActivity implements IRecuperaProdutoCategoria, IRecuperaImagemDownload {

    RelativeLayout progresso;
    RelativeLayout progressoUpList;
    ListView listViewAdapter;
    ArrayList<Produto> produtos = new ArrayList<>();
    RecuperaProdutoCategoriaApi produtoCatApi;
    Produto produto;
    DownloadImagemApi imagapi;
    private static ListViewAdapterCategoria listAdapter;
    ArrayList<Integer> limites = new ArrayList<>();
    TextView txtTituloHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);

        progresso = (RelativeLayout) findViewById(R.id.progressBarLayout);

        progressoUpList = (RelativeLayout) findViewById(R.id.progressBarLayoutCategoria);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        progresso.setVisibility(View.VISIBLE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarHome);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtTituloHome = (TextView) findViewById(R.id.txtTituloHome);
        txtTituloHome.setText("placas de video");

        Bundle bundleCategoria = getIntent().getExtras();
        final int idCategoria = bundleCategoria.getInt("idCategoria");

        produtoCatApi = new RecuperaProdutoCategoriaApi(CategoriaActivity.this, idCategoria, 0);

        produtoCatApi.delegate = CategoriaActivity.this;
        produtoCatApi.execute();

        listViewAdapter = (ListView) findViewById(R.id.listViewAdapterCustomCategoria);

        listViewAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Produto produto = produtos.get(position);

                Intent it = new Intent(CategoriaActivity.this, DescricaoProdutoActivity.class);

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

        listViewAdapter.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int currentVisibleItemCount;
            private int currentScrollState;
            private int currentFirstVisibleItem;
            private int totalItem;
            private LinearLayout lBelow;


            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // TODO Auto-generated method stub
                this.currentScrollState = scrollState;
                this.isScrollCompleted();
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                // TODO Auto-generated method stub
                this.currentFirstVisibleItem = firstVisibleItem;
                this.currentVisibleItemCount = visibleItemCount;
                this.totalItem = totalItemCount;


            }

            private void isScrollCompleted() {
                if (totalItem - currentFirstVisibleItem == currentVisibleItemCount
                        && this.currentScrollState == SCROLL_STATE_IDLE) {

                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    produtos.clear();

                    progressoUpList.setVisibility(View.VISIBLE);

                    produtoCatApi = new RecuperaProdutoCategoriaApi(CategoriaActivity.this, idCategoria, 10);

                    produtoCatApi.delegate = CategoriaActivity.this;
                    produtoCatApi.execute();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void preencheProdutoCategoria(String retorno) {
        try {

            JSONObject produtoObject = new JSONObject(retorno);
            JSONArray produtoArray = produtoObject.getJSONArray("data");

            URL url = null;
            Bitmap imgConvertBanner = null;
            final ArrayList<String> st = new ArrayList<>();;


            for (int i = 0; i < produtoArray.length(); i++) {
                produto = new Produto();
                produto.setNome(produtoArray.getJSONObject(i).getString("nome"));
                String descricao = produtoArray.getJSONObject(i).getString("descricao");
                Spanned formatHtmlDescricao = Html.fromHtml(descricao);
                produto.setDescricao(formatHtmlDescricao);
                produto.setUrlImagem(produtoArray.getJSONObject(i).getString("urlImagem"));
                produto.setPrecoDe(produtoArray.getJSONObject(i).getDouble("precoDe"));
                produto.setPrecoPor(produtoArray.getJSONObject(i).getDouble("precoPor"));
                produto.setId(produtoArray.getJSONObject(i).getInt("id"));
                st.add(produtoArray.getJSONObject(i).getString("urlImagem"));
                produtos.add(produto);
            }

            Imagem img = new Imagem();
            img.setTipo("Categoria");
            imagapi = new DownloadImagemApi(st, img);
            imagapi.delegate = CategoriaActivity.this;
            imagapi.execute();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void preencheImagenss(Imagem bmps) {
        int count = 0;
        switch (bmps.getTipo().toString()){
            case "Categoria":
                count = 0;
                for(Produto p : produtos){
                    p.setImgProduto(bmps.getBmps().get(count++));
                }
                listAdapter = new ListViewAdapterCategoria(produtos, getApplicationContext());

                listViewAdapter.setAdapter(listAdapter);
                progresso.setVisibility(View.INVISIBLE);
                if(progressoUpList != null) {
                    progressoUpList.setVisibility(View.INVISIBLE);
                }
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                break;
            default:
                break;
        }
    }
}
