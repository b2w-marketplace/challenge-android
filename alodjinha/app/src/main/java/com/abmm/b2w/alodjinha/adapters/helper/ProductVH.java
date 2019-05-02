package com.abmm.b2w.alodjinha.adapters.helper;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.abmm.b2w.alodjinha.LodjinhaApp;
import com.abmm.b2w.alodjinha.R;
import com.abmm.b2w.alodjinha.activities.product_detailed.ProductDetailsActivity;
import com.abmm.b2w.alodjinha.model.Product;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.parceler.Parcels;

import java.text.NumberFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.abmm.b2w.alodjinha.utils.Constants.Keys.PRODUCT_DATA;

public class ProductVH extends RecyclerView.ViewHolder {

    @BindView(R.id.element_product_layout) ConstraintLayout mLayout;
    @BindView(R.id.element_product_description_txt) TextView mDescription;
    @BindView(R.id.element_product_originalprice_txt) TextView mOriginalPrice;
    @BindView(R.id.element_product_sellingprice_txt) TextView mSellingPrice;
    @BindView(R.id.element_product_image) ImageView mProductImage;

    private final View view;
    private Product mProduct;

    private NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    public ProductVH(@NonNull View view) {
        super(view);
        ButterKnife.bind(this, view);
        this.view = view;
    }

    public void bind(Product element) {
        this.mProduct = element;
        mDescription.setText(element.getDescription());

        String originalPrice = currencyFormatter.format(element.getOriginalPrice());
        mOriginalPrice.setText(originalPrice);
        mOriginalPrice.setPaintFlags(mOriginalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        String sellingPrice = currencyFormatter.format(element.getSellingPrice());
        mSellingPrice.setText(sellingPrice);

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
                .apply(new RequestOptions()
                        .placeholder(R.drawable.loading_banner_image)
                        .error(R.drawable.logo_menu))
                .into(mProductImage);
    }
}