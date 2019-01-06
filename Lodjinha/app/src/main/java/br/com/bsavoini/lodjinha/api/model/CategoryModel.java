package br.com.bsavoini.lodjinha.api.model;

import com.google.gson.annotations.SerializedName;

public class CategoryModel {
    @SerializedName("id")
    private int id;

    @SerializedName("descricao")
    private String description;

    @SerializedName("urlImagem")
    private String imageURL;

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getImageURL() {
        return imageURL;
    }
}
