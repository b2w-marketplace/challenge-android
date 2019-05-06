package br.com.douglas.fukuhara.lodjinha.network.vo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductBestSellerVo {

    @SerializedName("data")
    private List<ProductDataVo> data;

    public List<ProductDataVo> getData() {
        return data;
    }

    public void setData(List<ProductDataVo> data) {
        this.data = data;
    }
}
