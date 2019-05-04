package com.abmm.b2w.alodjinha.adapters.holder;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.abmm.b2w.alodjinha.LodjinhaApp;
import com.abmm.b2w.alodjinha.R;
import com.abmm.b2w.alodjinha.activities.product_details.ProductDetailsActivity;
import com.abmm.b2w.alodjinha.model.Product;
import com.abmm.b2w.alodjinha.utils.GlideRequestListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

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
        String title = LodjinhaApp.getInstance().getString(R.string.product_title, element.getName(), element.getDescription());
        mDescriptionTxt.setText(title);

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
                .listener(new GlideRequestListener(mProgressBar))
                .apply(new RequestOptions().error(R.drawable.logo_menu))
                .into(mProductImg);
    }
}