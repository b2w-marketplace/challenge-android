package com.abmm.b2w.alodjinha.activities.product_details;

import android.content.DialogInterface;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.Spanned;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.abmm.b2w.alodjinha.R;
import com.abmm.b2w.alodjinha.activities.BaseAppCompatActivity;
import com.abmm.b2w.alodjinha.model.Product;
import com.abmm.b2w.alodjinha.utils.Constants.Keys;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.OnClick;

public class ProductDetailsActivity extends BaseAppCompatActivity implements ProductDetailsPresenterImpl.IProductDetailsView {

    @BindView(R.id.toolbar_layout) CollapsingToolbarLayout mCtl;
    @BindView(R.id.app_bar) AppBarLayout mAppBarLayout;

    @BindView(R.id.product_details_toolbar_img) ImageView mPictureImg;
    @BindView(R.id.product_originalprice_txt) TextView mOriginalPriceTxt;
    @BindView(R.id.product_sellingprice_txt) TextView mSellingPriceTxt;
    @BindView(R.id.product_long_title_txt) TextView mLongTitleTxt;
    @BindView(R.id.product_title_txt) TextView mTitleTxt;
    @BindView(R.id.product_description_txt) TextView mDescriptionTxt;
    @BindView(R.id.product_details_fab) FloatingActionButton mFabBtn;

    private IProductDetailsPresenter presenter;

    @Override
    protected int getLayout() {
        return R.layout.activity_product;
    }

    @Override
    protected void initUi() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Product product = Parcels.unwrap(getIntent().getParcelableExtra(Keys.PRODUCT_DATA));

        this.presenter = new ProductDetailsPresenterImpl(this);
        this.presenter.setProduct(product);

        setTitle(product.getCategory().getName());
        int transparent = ContextCompat.getColor(ProductDetailsActivity.this, android.R.color.transparent);
        mCtl.setExpandedTitleColor(transparent);

        setImage(product.getPictUrl());

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
        blockUi();
        presenter.requestProduct();
    }

    @OnClick(R.id.product_details_fab)
    public void sendProduct(View view) {
        mFabBtn.setEnabled(false);
        presenter.reserveProduct();
    }

    @Override
    public void showData() {
        Product product = presenter.getProduct();
        String title = product.getName();
        String originalPrice = getString(R.string.product_from, product.getOriginalPriceFormatted());
        String sellingPrice = getString(R.string.product_by, product.getSellingPriceFormatted());
        Spanned description = getTextFromHtml(product.getDescription());
        String category = product.getCategory().getName();
        String urlImg = product.getPictUrl();

        setImage(urlImg);
        mLongTitleTxt.setText(title);
        mOriginalPriceTxt.setText(originalPrice);
        mOriginalPriceTxt.setPaintFlags(mOriginalPriceTxt.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        mSellingPriceTxt.setText(sellingPrice);
        mTitleTxt.setText(category);
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

    @Override
    public void openAlertDialog(int resId) {
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        };
        openAlertDialog(getString(resId), listener);
    }

    @Override
    public void openAlertDialog(String msg, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg);
        builder.setCancelable(false);
        builder.setPositiveButton(android.R.string.ok, listener);

        AlertDialog dialog = builder.create();
        try {
            dialog.show();
        } catch (Exception ignored) {}
    }

    @Override
    public void releaseButton() {
        mFabBtn.setEnabled(true);
    }

}
