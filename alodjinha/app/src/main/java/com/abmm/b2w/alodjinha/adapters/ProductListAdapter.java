package com.abmm.b2w.alodjinha.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.abmm.b2w.alodjinha.R;
import com.abmm.b2w.alodjinha.activities.product_list.IProductListPresenter;
import com.abmm.b2w.alodjinha.adapters.helper.ProductVH;
import com.abmm.b2w.alodjinha.http_module.paging.OnLoadMoreListener;
import com.abmm.b2w.alodjinha.model.Product;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListAdapter extends RecyclerView.Adapter {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    private IProductListPresenter ctx;

    // The minimum amount of items to have below your current scroll position
    // before loading more.
    private int visibleThreshold = 18;
    private int lastVisibleItem, totalItemCount;
    private boolean isLoading;
    private OnLoadMoreListener onLoadMoreListener;

    public ProductListAdapter(IProductListPresenter context) {
        this.ctx = context;


        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) ctx.getRecyclerView().getLayoutManager();
        ctx.getRecyclerView().addOnScrollListener(getScrollListener(linearLayoutManager));
    }

    private RecyclerView.OnScrollListener getScrollListener(final LinearLayoutManager linearLayoutManager) {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
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
            final Product product = ctx.getProductList().get(i);
            ((ProductVH) viewHolder).bind(product);
        } else if (viewHolder instanceof LoadingVH) {
            ((LoadingVH) viewHolder).progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return ctx.getProductList().size();
    }

    @Override
    public int getItemViewType(int position) {
        return ctx.getProductList().get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.onLoadMoreListener = mOnLoadMoreListener;
    }

    public void setNewItem(Product newItem) {
        ctx.getProductList().add(newItem);
        notifyItemInserted(ctx.getProductList().size() - 1);
    }

    public void removeLastItem() {
        ctx.getProductList().remove(ctx.getProductList().size() - 1);
        notifyItemRemoved(ctx.getProductList().size());
    }

    public void setNewItems(boolean forceReload) {
        if (forceReload) {
            forceReload();
        } else {
            notifyDataSetChanged();
        }
        isLoading = false;
    }

    private void forceReload() {
        RecyclerView.LayoutManager layoutManagerTemp = ctx.getRecyclerView().getLayoutManager();
        ctx.getRecyclerView().setAdapter(null);
        ctx.getRecyclerView().setLayoutManager(null);
        ctx.getRecyclerView().setAdapter(this);
        ctx.getRecyclerView().setLayoutManager(layoutManagerTemp);
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
