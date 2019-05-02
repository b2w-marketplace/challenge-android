package com.abmm.b2w.alodjinha.activities.product_list;

import android.support.v7.widget.RecyclerView;

import com.abmm.b2w.alodjinha.model.Category;
import com.abmm.b2w.alodjinha.model.Product;

import java.util.List;

public interface IProductListPresenter {

    void setCategory(Category category);

    List<Product> getProductList();

    void loadNextPage();

    RecyclerView getRecyclerView();
}
