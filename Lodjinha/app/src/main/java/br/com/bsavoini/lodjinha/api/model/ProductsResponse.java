package br.com.bsavoini.lodjinha.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductsResponse {

    @SerializedName("data")
    private List<ProductModel> productsArr;

    @SerializedName("offset")
    private Integer offset;

    @SerializedName("total")
    private Integer total;

    public List<ProductModel> getProductsArr() {
        return productsArr;
    }

    public Integer getOffset() {
        return offset;
    }

    public Integer getTotal() {
        return total;
    }
}
