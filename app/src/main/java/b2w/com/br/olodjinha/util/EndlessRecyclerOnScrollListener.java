package b2w.com.br.olodjinha.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {

    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 10;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    private int current_page = 0;

    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView mRecyclerView;
    private int mDistanceFromBottom;

    public EndlessRecyclerOnScrollListener(LinearLayoutManager linearLayoutManager,
                                           RecyclerView recyclerView) {
        this.mLinearLayoutManager = linearLayoutManager;
        this.mRecyclerView = recyclerView;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        mDistanceFromBottom = mRecyclerView.computeVerticalScrollRange() -
                mRecyclerView.computeVerticalScrollOffset() - mRecyclerView.getHeight();

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLinearLayoutManager.getItemCount();
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount)
                <= (firstVisibleItem + visibleThreshold)) {
            current_page++;

            onLoadMore(current_page, mDistanceFromBottom);
            loading = true;
        }
    }

    public abstract void onLoadMore(int current_page, int distanceFromBottom);
}

