package br.com.soulblighter.lodjinha.ui.detail;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MenuItem;

import com.squareup.picasso.Picasso;

import java.util.Locale;

import br.com.soulblighter.lodjinha.R;
import br.com.soulblighter.lodjinha.databinding.DetailActivityBinding;
import br.com.soulblighter.lodjinha.model.Produto;
import br.com.soulblighter.lodjinha.rest.LodjinhaRetrofigConfig;
import br.com.soulblighter.lodjinha.rest.LodjinhaService;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_PRODUTO = "EXTRA_PRODUTO";

    private DetailActivityBinding mBinding;
    private CompositeDisposable mDisposables;
    private LodjinhaService mLodjinhaService;
    private boolean mLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.detail_activity);
        setSupportActionBar(mBinding.toolbar);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        mBinding.collapsingToolbar.setExpandedTitleColor(Color.TRANSPARENT);
        mBinding.collapsingToolbar.setCollapsedTitleTextColor(
                getResources().getColor(R.color.Whitetwo));

        Produto produto = getIntent().getParcelableExtra(EXTRA_PRODUTO);

        getSupportActionBar().setTitle(produto.getNome());

        mDisposables = new CompositeDisposable();
        mLodjinhaService = new LodjinhaRetrofigConfig(this).getLodjinhaService();


        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setCancelable(true)
                .setMessage(R.string.detail_reservar_sucesso)
                .setPositiveButton(R.string.detail_reservar_ok,
                        (dialog, id) -> dialog.dismiss()
                ).create();

        mBinding.fab.setOnClickListener(view -> {
            if(mLoading) {
                return;
            }
            mLoading = true;
            mDisposables.add(mLodjinhaService.reservarProduto(produto.getId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(produtoPostResp -> {
                        if (produtoPostResp.getResult()
                                .equalsIgnoreCase(getString(R.string.detail_reservar_result_success))) {
                            alertDialog.setMessage(getString(R.string.detail_reservar_sucesso));
                        } else {
                            alertDialog.setMessage(getString(R.string.detail_reservar_falha));
                        }
                        alertDialog.show();
                        mLoading = false;
                    }, t -> {
                        alertDialog.setMessage(getString(R.string.detail_reservar_falha));
                        alertDialog.show();
                        t.printStackTrace();
                        mLoading = false;
                    }));
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mBinding.precoOriginal.setPaintFlags(mBinding.precoOriginal.getPaintFlags() |
                Paint.STRIKE_THRU_TEXT_FLAG);

        mBinding.precoFinal.setText(
                getString(R.string.produto_preco_por,
                        String.format(Locale.US, "%.02f", produto.getPrecoPor())));
        mBinding.precoOriginal.setText(
                getString(R.string.produto_preco_de,
                        String.format(Locale.US, "%.02f", produto.getPrecoDe())));
        mBinding.nome.setText(produto.getNome());

        mDisposables.add(
                Observable.fromCallable(() -> Html.fromHtml(produto.getDescricao()))
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(mBinding.descricao::setText,
                                Throwable::printStackTrace));

        Picasso.with(this).load(produto.getUrlImagem()).into(mBinding.thumbnail);
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
}
