package com.abmm.b2w.alodjinha.activities.product_details;

import com.abmm.b2w.alodjinha.model.Product;

public interface IProductDetailsPresenter {
    void setProduct(Product product);

    Product getProduct();

    void requestProduct();

    void reserveProduct();
}
