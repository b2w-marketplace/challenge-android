package com.abmm.b2w.alodjinha.activities.product_list;

import com.abmm.b2w.alodjinha.http_module.ILodjinhaApi;
import com.abmm.b2w.alodjinha.http_module.LodjinhaApiClient;
import com.abmm.b2w.alodjinha.http_module.paging.PagingDataManager;
import com.abmm.b2w.alodjinha.model.Category;

import java.util.HashMap;

public class ProductListPresenterImpl implements IProductListPresenter {

    private Category mCategory;
    private PagingDataManager listDataManager;


    private final ILodjinhaApi api = LodjinhaApiClient.buildApiClient();

    @Override
    public void setCategory(Category category) {
        this.mCategory = category;
    }

    @Override
    public Category getCategory() {
        return mCategory;
    }

    @Override
    public void getProductsList() {
        api.getProducts(new HashMap<String, Integer>());
    }

    interface IProductListView {

    }
}
