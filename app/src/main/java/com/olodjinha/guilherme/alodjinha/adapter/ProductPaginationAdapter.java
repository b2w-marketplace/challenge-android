package com.olodjinha.guilherme.alodjinha.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.olodjinha.guilherme.alodjinha.R;
import com.olodjinha.guilherme.alodjinha.model.Product;
import com.olodjinha.guilherme.alodjinha.utils.AppUtils;
import com.olodjinha.guilherme.alodjinha.utils.PaginationAdapterCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guilherme on 21/06/2018.
 */

public class ProductPaginationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static ProductPaginationAdapter getInstance() {
        return mInstance;
    }

    private static final int ITEM = 0;
    private static final int LOADING = 1;

    public List<Product> productResults;
    private Context context;

    private static ProductPaginationAdapter mInstance;

    private boolean isLoadingAdded = false;
    private boolean retryPageLoad = false;

    private static ProductPaginationAdapter.MyClickListener myClickListener;

    private PaginationAdapterCallback mCallback;

    public ProductPaginationAdapter(Context context) {
        this.context = context;
        this.mCallback = (PaginationAdapterCallback) context;
        productResults = new ArrayList<>();
        mInstance = this;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                View viewItem = inflater.inflate(R.layout.product_adapter_item_pagination, parent, false);
                viewHolder = new ProductVH(viewItem);
                break;
            case LOADING:
                View viewLoading = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new LoadingVH(viewLoading);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Product product = productResults.get(position);

        switch (getItemViewType(position)) {

            case ITEM:
                final ProductVH productVH = (ProductVH) holder;

                productVH.textViewProduct.setText(product.getNome());
                productVH.textViewDescriptionProduct.setText(product.getDescricao());
                productVH.textViewPriceOld.setText("De:" + AppUtils.formatToMoney(context, product.getPrecoDe()));
                productVH.textViewPriceOld.setPaintFlags(productVH.textViewPriceOld.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                productVH.textViewPriceNew.setText("Por " + AppUtils.formatToMoney(context, product.getPrecoPor()));
                Glide.with(context).load(product.getUrlImagem()).into(productVH.imageViewProduct);
                break;

            case LOADING:
                LoadingVH loadingVH = (LoadingVH) holder;
                if (retryPageLoad) {
                    loadingVH.mProgressBar.setVisibility(View.GONE);
                } else {
                    loadingVH.mProgressBar.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return productResults == null ? 0 : productResults.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == productResults.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    public void add(Product r) {
        productResults.add(r);
        notifyItemInserted(productResults.size() - 1);
    }

    public void addAll(List<Product> moveResults) {
        for (Product result : moveResults) {
            add(result);
        }
    }

    public void remove(Product r) {
        int position = productResults.indexOf(r);
        if (position > -1) {
            productResults.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Product());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = productResults.size() - 1;
        Product result = getItem(position);

        if (result != null) {
            productResults.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Product getItem(int position) {
        return productResults.get(position);
    }

    protected class ProductVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textViewProduct, textViewDescriptionProduct, textViewPriceOld, textViewPriceNew;
        public ImageView imageViewProduct;

        public ProductVH(View view) {
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

    public void setOnItemClickListener(ProductPaginationAdapter.MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

    protected class LoadingVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ProgressBar mProgressBar;

        public LoadingVH(View itemView) {
            super(itemView);
            mProgressBar = itemView.findViewById(R.id.loadmore_progress);        }

        @Override
        public void onClick(View view) {
            //TODO
        }
    }
}