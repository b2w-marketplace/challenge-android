package br.com.kakobotasso.lodjinha.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class DataBannerContainer {
    @SerializedName("data")
    private List<Banner> dataBannerList = new ArrayList<>();

    public List<Banner> getDataModelList() {
        return dataBannerList;
    }
}
