package br.com.douglas.fukuhara.lodjinha.network.vo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductVo {

    @SerializedName("data")
    private List<ProductDataVo> data;

    @SerializedName("offset")
    private Integer offset;

    @SerializedName("total")
    private Integer total;

    public List<ProductDataVo> getData() {
        return data;
    }

    public void setData(List<ProductDataVo> data) {
        this.data = data;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
