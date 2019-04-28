package com.abmm.b2w.alodjinha.adapters;

import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abmm.b2w.alodjinha.R;
import com.abmm.b2w.alodjinha.activities.main.IMainPresenter;
import com.abmm.b2w.alodjinha.model.Product;
import com.abmm.b2w.alodjinha.utils.Constants.General;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.NumberFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopSellerAdapter extends RecyclerView.Adapter<TopSellerAdapter.TopSellerVH> {

    private final IMainPresenter ctx;

    private final NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    public TopSellerAdapter(IMainPresenter context) {
        this.ctx = context;
    }

    @NonNull
    @Override
    public TopSellerVH onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_top_seller, parent, false);
        return new TopSellerVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TopSellerVH holder, int position) {
        final Product element = ctx.getProductList().get(position);
        holder.bind(element);
    }

    @Override
    public int getItemCount() {
        if (ctx.getProductList() == null) {
            return General.EMPTY_LIST;
        }
        return ctx.getProductList().size();
    }

    class TopSellerVH extends RecyclerView.ViewHolder {

        @BindView(R.id.element_main_topseller_description_txt) TextView mDescription;
        @BindView(R.id.element_main_topseller_originalprice_txt) TextView mOriginalPrice;
        @BindView(R.id.element_main_topseller_sellingprice_txt) TextView mSellingPrice;
        @BindView(R.id.element_main_topseller_image) ImageView mProductImage;

        final View view;
        TopSellerVH(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;
        }

        void bind(Product element) {
            mDescription.setText(element.getDescription());

            String originalPrice = currencyFormatter.format(element.getOriginalPrice());
            mOriginalPrice.setText(originalPrice);
            mOriginalPrice.setPaintFlags(mOriginalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            String sellingPrice = currencyFormatter.format(element.getSellingPrice());
            mSellingPrice.setText(sellingPrice);

            setImage(element);
        }

        void setImage(Product item) {
            Glide.with(view)
                    .load(item.getPictUrl())
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.loading_banner_image)
                            .error(R.drawable.logo_menu))
                    .into(mProductImage);
        }
    }
}
