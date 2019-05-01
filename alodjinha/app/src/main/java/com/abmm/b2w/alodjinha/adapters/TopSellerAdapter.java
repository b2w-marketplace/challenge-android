package com.abmm.b2w.alodjinha.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abmm.b2w.alodjinha.R;
import com.abmm.b2w.alodjinha.activities.main.IMainPresenter;
import com.abmm.b2w.alodjinha.adapters.helper.ProductVH;
import com.abmm.b2w.alodjinha.model.Product;
import com.abmm.b2w.alodjinha.utils.Constants.General;

public class TopSellerAdapter extends RecyclerView.Adapter<ProductVH> {

    private final IMainPresenter ctx;

    public TopSellerAdapter(IMainPresenter context) {
        this.ctx = context;
    }

    @NonNull
    @Override
    public ProductVH onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductVH holder, int position) {
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

}
