package com.olodjinha.guilherme.alodjinha.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.olodjinha.guilherme.alodjinha.R;
import com.olodjinha.guilherme.alodjinha.model.Product;
import com.olodjinha.guilherme.alodjinha.utils.AppUtils;

import java.util.List;

/**
 * Created by Guilherme on 20/06/2018.
 */

public class BestSellersAdapter extends RecyclerView.Adapter<BestSellersAdapter.MyViewHolder> {

    public static BestSellersAdapter getInstance() {
        return mInstance;
    }

    private Context mContext;
    public List<Product> productList;
    private static MyClickListener myClickListener;
    private static BestSellersAdapter mInstance;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textViewProduct, textViewDescriptionProduct, textViewPriceOld, textViewPriceNew;
        public ImageView imageViewProduct;

        public MyViewHolder(View view) {
            super(view);
            textViewProduct = view.findViewById(R.id.textViewProduct);
            textViewDescriptionProduct = view.findViewById(R.id.textViewDescriptionProduct);
            textViewPriceOld = view.findViewById(R.id.textViewPriceOld);
            textViewPriceNew = view.findViewById(R.id.textViewPriceNew);
            imageViewProduct = view.findViewById(R.id.imageViewProduct);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public BestSellersAdapter(Context mContext, List<Product> productList) {
        this.mContext = mContext;
        this.productList = productList;
        mInstance = this;
    }

    @Override
    public BestSellersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_adapter, parent, false);
        return new BestSellersAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final BestSellersAdapter.MyViewHolder holder, int position) {

        Product product = productList.get(position);
        holder.textViewProduct.setText(product.getNome());
        holder.textViewDescriptionProduct.setText(product.getNome());
        holder.textViewPriceOld.setText("De:" + AppUtils.formatToMoney(mContext, product.getPrecoDe()));
        holder.textViewPriceOld.setPaintFlags(holder.textViewPriceOld.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.textViewPriceNew.setText("Por " + AppUtils.formatToMoney(mContext, product.getPrecoPor()));

        Glide.with(mContext).load(product.getUrlImagem()).into(holder.imageViewProduct);
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
