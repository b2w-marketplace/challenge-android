package br.com.bsavoini.lodjinha.catalog;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.AbsListView;

public class CatalogScrollListener extends RecyclerView.OnScrollListener {
    private boolean isScrolling = false;
    private CatalogScrollingCallback catalogScrollingCallback;

    public CatalogScrollListener(CatalogScrollingCallback catalogScrollingCallback) {
        this.catalogScrollingCallback = catalogScrollingCallback;
    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
            isScrolling = true;
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

        int currentItems = layoutManager.getChildCount();
        int totalItems = layoutManager.getItemCount();
        int scrollOutItems = layoutManager.findFirstVisibleItemPosition();

        if (isScrolling && dy > 0 && (currentItems + scrollOutItems == totalItems)) {
            isScrolling = false;
            catalogScrollingCallback.onRecyclerScrolledBottom();
        }
    }

    public interface CatalogScrollingCallback {
        void onRecyclerScrolledBottom();
    }
}
