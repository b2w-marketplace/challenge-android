package com.abmm.b2w.alodjinha.http_module.paging;

import com.abmm.b2w.alodjinha.model.Product;
import com.abmm.b2w.alodjinha.utils.Constants.Paging;

import java.util.ArrayList;
import java.util.List;

public class PagingDataManager {

    private static PagingDataManager instance;

    private int offset = Paging.DEFAULT_FIRST_ELEMENT;
    private int limit = Paging.DEFAULT_PAGE_SIZE;
    private boolean hasMoreData = true;

    private List<Product> components;

    private PagingDataManager() {
        this.components = new ArrayList<>();
    }

    public static PagingDataManager getInstance() {
        if (instance == null) {
            instance = new PagingDataManager();
        }
        return instance;
    }

    public List<Product> getComponents() {
        return components;
    }

    public void appendNewItems(List<Product> components) {
        this.components.addAll(components);
    }

    public void clearComponents() {
        this.components.clear();
    }

    public void updateForNextPage(int lastRequestSize) {
        if (lastRequestSize != 0) {
            incrementToNextOffset();
        } else {
            setHasMoreDataOFF();
        }
    }

    public void destroyerComponentManager() {
        instance = null;
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    public void incrementToNextOffset() {
        this.offset = limit;
        this.limit += Paging.DEFAULT_PAGE_SIZE;
    }

    public boolean hasMoreData() {
        return hasMoreData;
    }

    public void setHasMoreDataOFF() {
        this.hasMoreData = false;
    }

    public void resetQueryParameters() {
        this.offset = Paging.DEFAULT_FIRST_ELEMENT;
        this.limit = Paging.DEFAULT_PAGE_SIZE;
        hasMoreData = true;
    }

}
