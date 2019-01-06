package br.com.bsavoini.lodjinha.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BannersResponse {
    @SerializedName("data")
    private  List<BannerModel> bannersArr;

    public List<BannerModel> getBannersArr() {
        return bannersArr;
    }
}
