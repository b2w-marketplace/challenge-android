package com.abmm.b2w.alodjinha.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.abmm.b2w.alodjinha.R;
import com.abmm.b2w.alodjinha.adapters.helper.ProductVH;
import com.abmm.b2w.alodjinha.http_module.paging.OnLoadMoreListener;
import com.abmm.b2w.alodjinha.model.Product;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListAdapter extends RecyclerView.Adapter {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    private List<Product> productList;
    private RecyclerView recyclerView;

    // The minimum amount of items to have below your current scroll position
    // before loading more.
    private int visibleThreshold = 18;
    private int lastVisibleItem, totalItemCount;
    private boolean isLoading;
    private OnLoadMoreListener onLoadMoreListener;

    public ProductListAdapter(List<Product> list, RecyclerView recyclerView) {
        this.productList = list;
        this.recyclerView = recyclerView;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(getScrollListener(linearLayoutManager));
    }

    private RecyclerView.OnScrollListener getScrollListener(final LinearLayoutManager linearLayoutManager) {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        };
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_product, viewGroup, false);
            return new ProductVH(v);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_loading, viewGroup, false);
            return new LoadingVH(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ProductVH) {
            final Product product = productList.get(i);
            ((ProductVH) viewHolder).bind(product);
        } else if (viewHolder instanceof LoadingVH) {
            ((LoadingVH) viewHolder).progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return productList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public void setLoaded() {
        isLoading = false;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.onLoadMoreListener = mOnLoadMoreListener;
    }

    public void setNewItem(Product newItem) {
        productList.add(newItem);
        notifyItemInserted(productList.size() - 1);
    }

    public void removeLastItem() {
        productList.remove(productList.size() - 1);
        notifyItemRemoved(productList.size());
    }

    public void setNewItems(boolean forceReload) {
        if (forceReload) {
            forceReload();
        } else {
            notifyDataSetChanged();
        }
        setLoaded();
    }

    private void forceReload() {
        RecyclerView.LayoutManager layoutManagerTemp = recyclerView.getLayoutManager();
        recyclerView.setAdapter(null);
        recyclerView.setLayoutManager(null);
        recyclerView.setAdapter(this);
        recyclerView.setLayoutManager(layoutManagerTemp);
        this.notifyDataSetChanged();
    }


    class LoadingVH extends RecyclerView.ViewHolder {
        @BindView(R.id.progress_bar) ProgressBar progressBar;

        LoadingVH(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
