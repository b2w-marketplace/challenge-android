package br.com.bsavoini.lodjinha.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoriesResponse {
    @SerializedName("data")
    private  List<CategoryModel> categoriesArr;

    public List<CategoryModel> getCategoriesArr() {
        return categoriesArr;
    }
}
