package com.abmm.b2w.alodjinha.http_module.paging;

import com.abmm.b2w.alodjinha.model.Product;

import java.util.ArrayList;
import java.util.List;

public class PagingDataManager{

    private PagedQueryParameters pagedQueryParameters = new PagedQueryParameters();
    private List<Product> components;

    private PagingDataManager() { this.components = new ArrayList<>(); }

    private static PagingDataManager instance;

    public static PagingDataManager getInstance() {
        if (instance == null) {
            instance = new PagingDataManager();
        }
        return instance;
    }

    public List<Product> getComponents() { return components; }

    public void appendNewItems(List<Product> components) { this.components.addAll(components); }

    public void setComponents(List<Product> components){ this.components = components; }

    public void clearComponents() {
        this.components.clear();
    }

    public boolean updateForNextPage(int currentOffset) {
        if (currentOffset != pagedQueryParameters.getPage()) {
            pagedQueryParameters.incrementToNextPage();
            return true;
        }
        pagedQueryParameters.setHasMoreData(false);
        return false;
    }

    public PagedQueryParameters getPagedQueryParameters() { return pagedQueryParameters; }

    public int getNextPage() { return pagedQueryParameters.getPage(); }

    public void destroyerComponentManager() { instance = null; }

}
