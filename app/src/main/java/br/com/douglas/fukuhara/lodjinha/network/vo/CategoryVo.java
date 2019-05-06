package br.com.douglas.fukuhara.lodjinha.network.vo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryVo {

    @SerializedName("data")
    private List<CategoryDataVo> data;

    public List<CategoryDataVo> getData() {
        return data;
    }

    public void setData(List<CategoryDataVo> data) {
        this.data = data;
    }
}
