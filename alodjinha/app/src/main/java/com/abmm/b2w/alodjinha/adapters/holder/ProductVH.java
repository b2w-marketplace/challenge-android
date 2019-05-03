package com.abmm.b2w.alodjinha.adapters.holder;

import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.abmm.b2w.alodjinha.LodjinhaApp;
import com.abmm.b2w.alodjinha.R;
import com.abmm.b2w.alodjinha.activities.product_details.ProductDetailsActivity;
import com.abmm.b2w.alodjinha.model.Product;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.abmm.b2w.alodjinha.utils.Constants.Keys.PRODUCT_DATA;

public class ProductVH extends RecyclerView.ViewHolder {

    @BindView(R.id.element_product_description_txt) TextView mDescriptionTxt;
    @BindView(R.id.element_product_originalprice_txt) TextView mOriginalPriceTxt;
    @BindView(R.id.element_product_sellingprice_txt) TextView mSellingPriceTxt;
    @BindView(R.id.element_product_image) ImageView mProductImg;
    @BindView(R.id.element_product_pbar) ProgressBar mProgressBar;

    private final View view;
    private Product mProduct;

    public ProductVH(@NonNull View view) {
        super(view);
        ButterKnife.bind(this, view);
        this.view = view;
    }

    public void bind(Product element) {
        this.mProduct = element;
        mDescriptionTxt.setText(element.getDescription());

        String originalPrice = element.getOriginalPriceFormatted();
        mOriginalPriceTxt.setText(originalPrice);
        mOriginalPriceTxt.setPaintFlags(mOriginalPriceTxt.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        String sellingPrice = element.getSellingPriceFormatted();
        mSellingPriceTxt.setText(sellingPrice);

        setImage(element);
    }

    @OnClick(R.id.element_product_layout)
    void openProductDetails() {
        Bundle extras = new Bundle();
        extras.putParcelable(PRODUCT_DATA, Parcels.wrap(this.mProduct));

        LodjinhaApp.getInstance().callActivity(ProductDetailsActivity.class, extras);
    }

    private void setImage(Product item) {
        Glide.with(view)
                .load(item.getPictUrl())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        mProgressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        mProgressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .apply(new RequestOptions()
                        .error(R.drawable.logo_menu))
                .into(mProductImg);
    }
}