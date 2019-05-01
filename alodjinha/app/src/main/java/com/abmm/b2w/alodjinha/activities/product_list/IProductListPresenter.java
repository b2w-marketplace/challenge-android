package com.abmm.b2w.alodjinha.activities.product_list;

import com.abmm.b2w.alodjinha.model.Category;

public interface IProductListPresenter {
    void setCategory(Category category);

    Category getCategory();

    void getProductsList();
}
