package br.com.douglas.fukuhara.lodjinha.network.vo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BannerVo {

    @SerializedName("data")
    private List<BannerDataVo> data;

    public List<BannerDataVo> getData() {
        return data;
    }

    public void setData(List<BannerDataVo> data) {
        this.data = data;
    }
}
