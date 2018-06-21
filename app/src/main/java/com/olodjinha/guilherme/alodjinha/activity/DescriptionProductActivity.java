package com.olodjinha.guilherme.alodjinha.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.olodjinha.guilherme.alodjinha.R;
import com.olodjinha.guilherme.alodjinha.model.Product;
import com.olodjinha.guilherme.alodjinha.model.ResultStatus;
import com.olodjinha.guilherme.alodjinha.rest.ApiClient;
import com.olodjinha.guilherme.alodjinha.rest.ApiInterface;
import com.olodjinha.guilherme.alodjinha.utils.AppUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Guilherme on 20/06/2018.
 */

public class DescriptionProductActivity extends AppCompatActivity {

    private static final String TAG = DescriptionProductActivity.class.getSimpleName();

    //region Views

    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    @BindView(R.id.imageViewProduct)
    public ImageView imageViewProduct;

    @BindView(R.id.textViewProduct)
    public TextView textViewProduct;

    @BindView(R.id.textViewPriceOld)
    public TextView textViewPriceOld;

    @BindView(R.id.textViewPriceNew)
    public TextView textViewPriceNew;

    @BindView(R.id.textViewDescription)
    public TextView textViewDescription;

    @BindView(R.id.floatingActionButtonBookProduct)
    public FloatingActionButton floatingActionButtonBookProduct;

    //endregion

    //region Constant. Vars

    public static String BUNDLE_PRODUCT = "com.olodjinha.guilherme.olodjinha.activity.DescriptionProductActivity.BUNDLE_PRODUCT";
    private Product product;

    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_product);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            product = (Product) extras.get(BUNDLE_PRODUCT);
        }
        getSupportActionBar().setTitle(product.getNome());
        callApiLoadProduct(product.getId());
    }

    private void callApiLoadProduct(int productId) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Product> call = apiService.getProductById(productId);

        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                product = response.body();
                if (product != null) {
                    updateUI(product);
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    private void updateUI(Product product) {
        Glide.with(getApplicationContext()).load(product.getUrlImagem()).into(imageViewProduct);
        textViewProduct.setText(product.getNome());
        textViewPriceOld.setText("De:" + AppUtils.formatToMoney(getApplicationContext(), product.getPrecoDe()));
        textViewPriceOld.setPaintFlags(textViewPriceOld.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        textViewPriceNew.setText("Por " + AppUtils.formatToMoney(getApplicationContext(), product.getPrecoPor()));
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            textViewDescription.setText(Html.fromHtml(product.getDescricao(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            textViewDescription.setText(Html.fromHtml(product.getDescricao()));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return true;
    }

    @OnClick(R.id.floatingActionButtonBookProduct)
    public void bookProduct() {

        floatingActionButtonBookProduct.setClickable(false);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResultStatus> call = apiService.postBookProduct(product.getId());

        call.enqueue(new Callback<ResultStatus>() {
            @Override
            public void onResponse(Call<ResultStatus> call, Response<ResultStatus> response) {
                ResultStatus resultStatus = response.body();
                if (resultStatus != null && resultStatus.getResult() != null && resultStatus.getResult().equals("success")) {
                    new MaterialDialog.Builder(DescriptionProductActivity.this)
                            .content(R.string.sucess_book_product)
                            .positiveText(R.string.ok)
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                            @Override
                                            public void onClick(MaterialDialog dialog, DialogAction which) {
                                                finish();
                                            }
                                        }
                            ).show();

                } else if (resultStatus != null && resultStatus.getResult() != null && resultStatus.getMensagem() != null && resultStatus.getResult().equals("error")) {
                    new MaterialDialog.Builder(DescriptionProductActivity.this)
                            .content(resultStatus.getMensagem())
                            .positiveText(R.string.ok)
                            .show();
                }
                floatingActionButtonBookProduct.setClickable(true);
            }

            @Override
            public void onFailure(Call<ResultStatus> call, Throwable t) {
                Log.e(TAG, t.toString());
                floatingActionButtonBookProduct.setClickable(true);
            }
        });
    }
}