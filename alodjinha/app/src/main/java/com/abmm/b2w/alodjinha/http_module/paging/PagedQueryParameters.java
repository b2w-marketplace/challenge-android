package com.abmm.b2w.alodjinha.http_module.paging;

public class PagedQueryParameters {

    private static int defaultPageNo = 0;

    private int page = 1;
    private boolean hasMoreData = true;

    public int getPage() {
        return page;
    }

    public void incrementToNextPage() {
        this.page++;
    }

    public boolean hasMoreData() {
        return hasMoreData;
    }

    public void setHasMoreData(boolean hasMoreData) {
        this.hasMoreData = hasMoreData;
    }

    public void resetQueryParameters() {
        page = defaultPageNo;
        hasMoreData = true;
    }
}
