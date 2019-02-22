package br.com.b2wmarketplace.alodjinha.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.facebook.drawee.backends.pipeline.Fresco;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import br.com.b2wmarketplace.alodjinha.MainActivity;
import br.com.b2wmarketplace.alodjinha.R;
import br.com.b2wmarketplace.alodjinha.adapters.BannerAdapter;
import br.com.b2wmarketplace.alodjinha.adapters.CategoriaAdapter;
import br.com.b2wmarketplace.alodjinha.adapters.ProdutoAdapter;
import br.com.b2wmarketplace.alodjinha.common.CirclePagerIndicatorDecoration;
import br.com.b2wmarketplace.alodjinha.common.ErroWs;
import br.com.b2wmarketplace.alodjinha.models.Banner;
import br.com.b2wmarketplace.alodjinha.models.Categoria;
import br.com.b2wmarketplace.alodjinha.models.Produto;
import br.com.b2wmarketplace.alodjinha.network.AsyncTaskCompleteListener;
import br.com.b2wmarketplace.alodjinha.network.WS;

public class HomeFragment extends Fragment {
    private Toolbar toolbar;
    private ConstraintLayout constraintLayout;
    private RecyclerView rvBanner, rvCategoria, rvMaisVendido;
    private ProgressBar pbProgresso;
    private ArrayList<Banner> listaBanner;
    private ArrayList<Categoria> listaCategoria;
    private ArrayList<Produto> listaProdutos;

    public HomeFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Fresco.initialize(view.getContext());
        inicializa(view);

        toolbar.setTitle("");

        if (getActivity() != null){
            ((MainActivity) getActivity()).setMenu(toolbar);
        }

        listaBanner = new ArrayList<>();
        listaCategoria = new ArrayList<>();
        listaProdutos = new ArrayList<>();

        rvBanner.setHasFixedSize(true);
        rvBanner.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvCategoria.setHasFixedSize(true);
        rvCategoria.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvMaisVendido.setHasFixedSize(true);
        rvMaisVendido.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        rvMaisVendido.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));

        carregaListaBannerWs();
        carregaListaCategoriaWs();
        carregaListaMaisVendidoWs();
    }

    private void inicializa(View view){
        toolbar =  view.findViewById(R.id.toolbar);
        constraintLayout = view.findViewById(R.id.constraintLayout);
        rvBanner =  view.findViewById(R.id.rvBanner);
        rvCategoria = view.findViewById(R.id.rvCategoria);
        rvMaisVendido = view.findViewById(R.id.rvProduto);
        pbProgresso = view.findViewById(R.id.pbProgresso);
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    private void carregaListaBannerWs(){
        new WS(new AsyncTaskCompleteListener<String>() {
            @Override
            public void onTaskComplete(String result) {
                retornoWs(result, true, false);
            }
        }, "0", "banner").execute();
    }

    private void carregaListaCategoriaWs(){
        new WS(new AsyncTaskCompleteListener<String>() {
            @Override
            public void onTaskComplete(String result) {
                retornoWs(result, false, true);
            }
        }, "0", "categoria").execute();
    }

    private void carregaListaMaisVendidoWs(){
        new WS(new AsyncTaskCompleteListener<String>() {
            @Override
            public void onTaskComplete(String result) {
                retornoWs(result, false, false);
            }
        }, "0", "produto/maisvendidos").execute();
    }

    public void retornoWs(String response, boolean banner, boolean categoria) {
        try {
            switch (response) {
                case "Erro":
                    ErroWs.retornaErro(getContext(), pbProgresso);
                    break;
                default:
                    if (banner){
                        carregaBannerWs(response);
                    }else if (categoria){
                        carregaCategoriaWs(response);
                    }else{
                        carregaMaisVendidoWs(response);
                    }
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            if (!banner && !categoria) ErroWs.retornaErro(getContext(), pbProgresso);
        }
    }

    private void carregaBannerWs(String response) throws JSONException {
        JSONObject jsonObjectData = new JSONObject(response);
        try {
            JSONArray jsonArray = jsonObjectData.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                String urlimagem = jsonObject.getString("urlImagem");

                Banner banner = new Banner();
                banner.setId(id);
                banner.setUrlimagem(urlimagem);
                listaBanner.add(banner);
            }
            BannerAdapter adapterBunner = new BannerAdapter(listaBanner, getContext());
            rvBanner.setAdapter(adapterBunner);
            rvBanner.addItemDecoration(new CirclePagerIndicatorDecoration());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void carregaCategoriaWs(String response) throws JSONException {
        JSONObject jsonObjectData = new JSONObject(response);
        try {
            JSONArray jsonArray = jsonObjectData.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                String descricao = jsonObject.getString("descricao");
                String urlimagem = jsonObject.getString("urlImagem");

                Categoria categoria = new Categoria();
                categoria.setId(id);
                categoria.setDescricao(descricao);
                categoria.setUrlimagem(urlimagem);
                listaCategoria.add(categoria);
            }
            CategoriaAdapter adapterCategoria = new CategoriaAdapter(listaCategoria, getContext());
            rvCategoria.setAdapter(adapterCategoria);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void carregaMaisVendidoWs(String response) throws JSONException {
        JSONObject jsonObjectData = new JSONObject(response);
        try {
            JSONArray jsonArray = jsonObjectData.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                String nome = jsonObject.getString("nome");
                String urlimagem = jsonObject.getString("urlImagem");
                String descricao = jsonObject.getString("descricao");
                double precode = jsonObject.getDouble("precoDe");
                double precopor = jsonObject.getDouble("precoPor");

                Produto produto = new Produto();
                produto.setId(id);
                produto.setNome(nome);
                produto.setUrlimagem(urlimagem);
                produto.setDescricao(descricao);
                produto.setPrecode(precode);
                produto.setPrecopor(precopor);

                listaProdutos.add(produto);
            }
            pbProgresso.setVisibility(View.GONE);
            if (getActivity() != null) {
                ProdutoAdapter adapterProdutos = new ProdutoAdapter(listaProdutos, getContext(), getActivity().getSupportFragmentManager());
                rvMaisVendido.setAdapter(adapterProdutos);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        constraintLayout.setVisibility(View.VISIBLE);
    }
}
