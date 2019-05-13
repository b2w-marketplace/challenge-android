package br.com.douglas.fukuhara.lodjinha.view;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;

import br.com.douglas.fukuhara.lodjinha.R;
import br.com.douglas.fukuhara.lodjinha.interfaces.ProductDetailContract;
import br.com.douglas.fukuhara.lodjinha.network.RetrofitImpl;
import br.com.douglas.fukuhara.lodjinha.network.vo.ProductDataVo;
import br.com.douglas.fukuhara.lodjinha.presenter.ProductDetailPresenter;

public class ProductDetailActivity extends AppCompatActivity
        implements ProductDetailContract.View {

    private static final String PRODUCT_BUNDLE_KEY = "product_bundle_key";

    private ProductDetailContract.Presenter mPresenter;
    private ImageView mIvProductImage;
    private TextView mTvProductTitle;
    private TextView mTvProductPrevPrice;
    private TextView mTvProductFinalPrice;
    private TextView mTvProductDesc;
    private FloatingActionButton mFabReserve;

    public static Intent newIntent(Context context, ProductDataVo productDataVo) {
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra(PRODUCT_BUNDLE_KEY, new Gson().toJson(productDataVo));
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ProductDataVo productDataVo = null;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey(PRODUCT_BUNDLE_KEY)) {
            productDataVo = new Gson().fromJson(bundle.getString(PRODUCT_BUNDLE_KEY), ProductDataVo.class);
            getSupportActionBar().setTitle(productDataVo.getCategoria().getDescricao());
        }

        setLayoutViews();

        mPresenter = new ProductDetailPresenter(productDataVo,
                this, RetrofitImpl.getInstance());
        mPresenter.displayProductDetail();
    }

    private void setLayoutViews() {
        mIvProductImage = findViewById(R.id.iv_product_image);
        mTvProductTitle = findViewById(R.id.tv_product_title);
        mTvProductPrevPrice = findViewById(R.id.tv_product_prev_price);
        mTvProductFinalPrice = findViewById(R.id.tv_product_final_price);
        mTvProductDesc = findViewById(R.id.tv_product_description);
        mFabReserve = findViewById(R.id.fab_product_reserve);

        mFabReserve.setOnClickListener(v -> mPresenter.onReserveButtonPressed());
    }

    @Override
    public void populateProductLayout(String urlImagem, String nome, String precoDe, String precoPor, String descricao) {
        Glide
                .with(this)
                .load(urlImagem)
                .error(R.mipmap.image_placeholder_error)
                .into(mIvProductImage);
        mTvProductTitle.setText(nome);
        mTvProductPrevPrice.setText(getString(R.string.best_seller_prev_price, precoDe));
        mTvProductFinalPrice.setText(getString(R.string.best_seller_final_price, precoPor));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mTvProductDesc.setText(Html.fromHtml(descricao, Html.FROM_HTML_MODE_COMPACT));
        } else {
            mTvProductDesc.setText(Html.fromHtml(descricao));
        }
    }

    @Override
    public void displayNoContentAvailable() {

    }

    @Override
    public void setFabEnabled(boolean enabled) {
        mFabReserve.setEnabled(enabled);
    }

    @Override
    public void showReservationSuccess() {
        buildAlertDialog(getString(R.string.dialog_product_reservation_success));
    }

    @Override
    public void showGenericFailureOnReservation() {
        buildAlertDialog(getString(R.string.dialog_product_reservation_fail));
    }

    @Override
    public void showFailureOnReservation(String serverMessage) {
        buildAlertDialog(serverMessage);
    }

    private void buildAlertDialog(String stringMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setMessage(stringMessage)
                .setPositiveButton(R.string.dialog_ok_button, (dialog, which) -> mPresenter.onDialogConfirmationPressed() )
                .setCancelable(false)
                .create()
                .show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onDestroy() {
        mPresenter.disposeAll();
        super.onDestroy();
    }
}
