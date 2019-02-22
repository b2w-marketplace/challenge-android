package br.com.b2wmarketplace.alodjinha.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import br.com.b2wmarketplace.alodjinha.MainActivity;
import br.com.b2wmarketplace.alodjinha.R;
import br.com.b2wmarketplace.alodjinha.adapters.ProdutoAdapter;
import br.com.b2wmarketplace.alodjinha.common.BackPressedFragment;
import br.com.b2wmarketplace.alodjinha.common.ErroWs;
import br.com.b2wmarketplace.alodjinha.common.PaginationScrollListener;
import br.com.b2wmarketplace.alodjinha.models.Produto;
import br.com.b2wmarketplace.alodjinha.network.AsyncTaskCompleteListener;
import br.com.b2wmarketplace.alodjinha.network.WS;
import butterknife.ButterKnife;

public class CategoriaProdutoFragment extends BackPressedFragment implements SwipeRefreshLayout.OnRefreshListener {
    private Toolbar toolbar;
    private RecyclerView rvProduto;
    private ProgressBar pbProgresso;
    private SwipeRefreshLayout swipeRefresh;

    private ProdutoAdapter adapterProdutos;
    private JSONArray jsonArray;

    private int id;
    private final int PAGE_START = 1;
    private int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private int totalPage;
    boolean isLoading = false;
    private int itemCount = 0;
    private final int ITEM_PAGE = 20;

    public CategoriaProdutoFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_categoria_produto, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inicializa(view);

        Bundle bundle;
        bundle = getArguments();
        if (bundle != null) {
            id = bundle.getInt("id");
            toolbar.setTitle(bundle.getString("descricao"));
        }
        if (getActivity() != null) {
            AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
            appCompatActivity.setSupportActionBar(toolbar);
            if (appCompatActivity.getSupportActionBar() != null) {
                appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
        setHasOptionsMenu(true);

        ButterKnife.bind(getActivity());

        swipeRefresh.setOnRefreshListener(this);
        rvProduto.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        rvProduto.setLayoutManager(layoutManager);
        rvProduto.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));

        rvProduto.addOnScrollListener(new PaginationScrollListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage++;
                preparedListItem();
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        carregaListaProdutoWs();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {
        if (getActivity() != null) {
            ((MainActivity) getActivity()).callBackPressed();
        }
    }

    private void inicializa(View view) {
        toolbar = view.findViewById(R.id.toolbar);
        rvProduto = view.findViewById(R.id.rvProduto);
        pbProgresso = view.findViewById(R.id.pbProgresso);
        swipeRefresh = view.findViewById(R.id.swipeRefresh);
    }

    public static CategoriaProdutoFragment newInstance() {
        return new CategoriaProdutoFragment();
    }

    private void carregaListaProdutoWs(){
        new WS(new AsyncTaskCompleteListener<String>() {
            @Override
            public void onTaskComplete(String result) {
                retornoWs(result);
            }
        }, "0", "produto?categoriaId=" + id).execute();
    }

    public void retornoWs(String response) {
        try {
            switch (response) {
                case "Erro":
                    ErroWs.retornaErro(getContext(), pbProgresso);
                    break;
                default:
                    JSONObject jsonObjectData = new JSONObject(response);
                    try {
                        jsonArray = jsonObjectData.getJSONArray("data");
                        if (getActivity() != null) {
                            adapterProdutos = new ProdutoAdapter(new ArrayList<Produto>(), getContext(), getActivity().getSupportFragmentManager());
                            rvProduto.setAdapter(adapterProdutos);
                        }
                        totalPage = (jsonArray.length() + (ITEM_PAGE - 1)) / ITEM_PAGE;
                        preparedListItem();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            ErroWs.retornaErro(getContext(), pbProgresso);
        }
    }

    private void preparedListItem() {
        final ArrayList<Produto> listaProdutos = new ArrayList<>();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                int qtdeproduto = jsonArray.length() - itemCount;

                for (int i = 0; i < qtdeproduto && i < ITEM_PAGE; i++) {
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(itemCount);
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

                        itemCount++;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if (itemCount <= ITEM_PAGE) pbProgresso.setVisibility(View.GONE);

                if (currentPage != PAGE_START) adapterProdutos.removeLoading();
                adapterProdutos.addAll(listaProdutos);
                swipeRefresh.setRefreshing(false);
                if (currentPage < totalPage){
                    adapterProdutos.addLoading();
                }else{
                    isLastPage = true;
                }
                isLoading = false;


            }
        }, 2000);
    }

    @Override
    public void onRefresh() {
        itemCount = 0;
        currentPage = PAGE_START;
        isLastPage = false;
        adapterProdutos.clear();
        preparedListItem();
    }
}
