package com.olodjinha.guilherme.alodjinha.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Guilherme on 19/06/2018.
 */

public class Data {

    @SerializedName("data")
    List<Banner> banner;

    public List<Banner> getBanner() {
        return banner;
    }

    public void setBanner(List<Banner> banner) {
        this.banner = banner;
    }
}