package com.olodjinha.guilherme.alodjinha.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Guilherme on 19/06/2018.
 */

public class DataCategory {

    @SerializedName("data")
    List<Category> category;

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }
}