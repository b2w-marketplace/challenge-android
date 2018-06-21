package com.olodjinha.guilherme.alodjinha.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Guilherme on 19/06/2018.
 */

public class DataProduct {

    @SerializedName("data")
    List<Product> product;

    @SerializedName("offset")
    int offset;

    @SerializedName("total")
    int total;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }
}
