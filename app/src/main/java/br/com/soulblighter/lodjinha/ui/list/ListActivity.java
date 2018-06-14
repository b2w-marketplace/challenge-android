package br.com.soulblighter.lodjinha.ui.list;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import br.com.soulblighter.lodjinha.R;
import br.com.soulblighter.lodjinha.databinding.ListActivityBinding;
import br.com.soulblighter.lodjinha.model.Categoria;
import br.com.soulblighter.lodjinha.rest.LodjinhaRetrofigConfig;
import br.com.soulblighter.lodjinha.rest.LodjinhaService;
import br.com.soulblighter.lodjinha.ui.detail.DetailActivity;
import br.com.soulblighter.lodjinha.ui.main.home.ProdutoAdapter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ListActivity extends AppCompatActivity {

    public static final String EXTRA_CATEGORIA = "EXTRA_CATEGORIA";

    private ListActivityBinding mBinding;
    private ProdutoAdapter mProdutoAdapter;

    private LodjinhaService mLodjinhaService;
    private CompositeDisposable mDisposables;

    private final int PAGE_SIZE = 20;
    private int mProdutoCount = 0;
    private int mOffset = 0;
    boolean isLoading = false;
    int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.list_activity);
        setSupportActionBar(mBinding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Categoria categoria = getIntent().getParcelableExtra(EXTRA_CATEGORIA);

        getSupportActionBar().setTitle(categoria.getDescricao());

        mLodjinhaService = new LodjinhaRetrofigConfig(this).getLodjinhaService();
        mDisposables = new CompositeDisposable();

        final LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mProdutoAdapter = new ProdutoAdapter(this);
        mBinding.recyclerview.setAdapter(mProdutoAdapter);
        mBinding.recyclerview.setLayoutManager(layoutManager);

        mProdutoAdapter.setListenner(produto -> {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_PRODUTO, produto);
            startActivity(intent);
        });

        loadMore(mOffset, PAGE_SIZE, categoria.getId());

        mProdutoAdapter.setLoading(true);

        mBinding.recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if (!isLoading && !hasLoadedAllItens()) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0) {
                        loadMore(mOffset, PAGE_SIZE, categoria.getId());
                    }
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        if (mDisposables != null && !mDisposables.isDisposed()) {
            mDisposables.dispose();
        }
        super.onDestroy();
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

    private void loadMore(int offset, int size, int id) {
        if(isLoading) {
            return;
        }
        isLoading = true;
        mDisposables.add(mLodjinhaService.getListaProdutos(offset, size, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(produtosResp -> {
                            mBinding.progress.setVisibility(View.GONE);
                            mProdutoAdapter.pushData(produtosResp.getData());
                            mProdutoCount = produtosResp.getTotal();
                            mOffset += produtosResp.getData().size();
                            isLoading = false;
                            if(produtosResp.getData().size() == 0) {
                                mProdutoCount = mOffset;
                            }
                            if(hasLoadedAllItens()) {
                                mProdutoAdapter.setLoading(false);
                            }
                        },
                        Throwable::printStackTrace)
        );
    }

    private boolean hasLoadedAllItens() {
        return mOffset >= mProdutoCount;
    }

}
