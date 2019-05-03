package com.abmm.b2w.alodjinha.activities.product_details;

import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abmm.b2w.alodjinha.R;
import com.abmm.b2w.alodjinha.activities.BaseAppCompatActivity;
import com.abmm.b2w.alodjinha.http_module.ILodjinhaApi;
import com.abmm.b2w.alodjinha.http_module.LodjinhaApiClient;
import com.abmm.b2w.alodjinha.model.ChartMessage;
import com.abmm.b2w.alodjinha.model.Product;
import com.abmm.b2w.alodjinha.model.enums.Result;
import com.abmm.b2w.alodjinha.utils.Constants.Keys;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends BaseAppCompatActivity {

    @BindView(R.id.toolbar_layout) CollapsingToolbarLayout mCtl;
    @BindView(R.id.app_bar) AppBarLayout mAppBarLayout;

    @BindView(R.id.product_details_toolbar_img) ImageView mPictureImg;
    @BindView(R.id.product_originalprice_txt) TextView mOriginalPriceTxt;
    @BindView(R.id.product_sellingprice_txt) TextView mSellingPriceTxt;
    @BindView(R.id.product_long_title_txt) TextView mLongTitleTxt;
    @BindView(R.id.product_title_txt) TextView mTitleTxt;
    @BindView(R.id.product_description_txt) TextView mDescriptionTxt;
    @BindView(R.id.product_details_fab) FloatingActionButton mFabBtn;

    private Product mProduct;

    private ILodjinhaApi api = LodjinhaApiClient.buildApiClient();

    @Override
    protected int getLayout() {
        return R.layout.activity_product;
    }

    @Override
    protected void initUi() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mProduct = Parcels.unwrap(getIntent().getParcelableExtra(Keys.PRODUCT_DATA));

        setTitle(mProduct.getCategory().getName());
        int transparent = ContextCompat.getColor(ProductDetailsActivity.this, android.R.color.transparent);
        mCtl.setExpandedTitleColor(transparent);

        setImage(mProduct.getPictUrl());

        mAppBarLayout.addOnOffsetChangedListener(getAppBarBehavior());
    }

    private AppBarLayout.OnOffsetChangedListener getAppBarBehavior() {
        return new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }

                if (scrollRange + verticalOffset == 0) {
                    int textColorTransparent = ContextCompat.getColor(ProductDetailsActivity.this, android.R.color.transparent);
                    int arrowColor = ContextCompat.getColor(ProductDetailsActivity.this, android.R.color.white);
                    toolbar.getNavigationIcon().setColorFilter(arrowColor, PorterDuff.Mode.SRC_ATOP);
                    mCtl.setExpandedTitleColor(textColorTransparent);
                    isShow = true;
                } else if (isShow) {
                    int textColor = ContextCompat.getColor(ProductDetailsActivity.this, android.R.color.white);
                    int arrowColor = ContextCompat.getColor(ProductDetailsActivity.this, R.color.dark);
                    toolbar.getNavigationIcon().setColorFilter(arrowColor, PorterDuff.Mode.SRC_ATOP);
                    mCtl.setCollapsedTitleTextColor(textColor);
                    isShow = false;
                }
            }
        };
    }

    @Override
    protected void makeRequests() {
        api.getProductById(mProduct.getId()).enqueue(handleProductCallback());
    }

    private Callback<Product> handleProductCallback() {
        return new Callback<Product>() {
            @Override
            public void onResponse(@NonNull Call<Product> call, @NonNull Response<Product> response) {
                if (response.isSuccessful()) {
                    mProduct = response.body();
                    showData();

                    Toast.makeText(ProductDetailsActivity.this, "hahaha :)", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProductDetailsActivity.this, "lascou =(", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Product> call, @NonNull Throwable t) {

            }
        };
    }

    @OnClick(R.id.product_details_fab)
    public void sendProduct(View view) {
        mFabBtn.setEnabled(false);
        api.addProductToChart(mProduct.getId()).enqueue(handleAddToChartCallback());
    }

    private Callback<ChartMessage> handleAddToChartCallback() {
        return new Callback<ChartMessage>() {
            @Override
            public void onResponse(@NonNull Call<ChartMessage> call, @NonNull Response<ChartMessage> response) {
                if (response.isSuccessful()) {
                    ChartMessage serverAnswer = response.body();

                    if (Result.SUCCESS.equalsIgnoreCase(serverAnswer.getStatus())) {
                        openAlertDialog(R.string.product_reserved_successfully);
                    } else {
                        openAlertDialog(serverAnswer.getMessage(), null);
                    }
                }
                mFabBtn.setEnabled(true);
            }

            @Override
            public void onFailure(@NonNull Call<ChartMessage> call, @NonNull Throwable t) {
                showError();
                mFabBtn.setEnabled(true);
            }
        };
    }

    private void showData() {
        String title = mProduct.getName();
        String originalPrice = getString(R.string.product_from, mProduct.getOriginalPriceFormatted());
        String sellingPrice = getString(R.string.product_by, mProduct.getSellingPriceFormatted());
        Spanned description = getTextFromHtml(mProduct.getDescription());
        String urlImg = mProduct.getPictUrl();

        setImage(urlImg);
        mLongTitleTxt.setText(title);
        mOriginalPriceTxt.setText(originalPrice);
        mSellingPriceTxt.setText(sellingPrice);
        mTitleTxt.setText(title);
        mDescriptionTxt.setText(description);
    }

    private void setImage(String urlImg) {
        Glide.with(this)
                .load(urlImg)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.loading_banner_image)
                        .error(R.drawable.logo_menu))
                .into(mPictureImg);
    }

    public Spanned getTextFromHtml(final String textResource) {
        Spanned textFormatted;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textFormatted = Html.fromHtml(textResource, Html.FROM_HTML_OPTION_USE_CSS_COLORS);
        } else {
            textFormatted = Html.fromHtml(textResource);
        }
        return textFormatted;
    }

    private void openAlertDialog(int resId) {
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        };
        openAlertDialog(getString(resId), listener);
    }

    private void openAlertDialog(String msg, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg);
        builder.setCancelable(false);
        builder.setPositiveButton(android.R.string.ok, listener);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
