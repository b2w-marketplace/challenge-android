package br.com.bsavoini.lodjinha.api.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CategoryModel implements Serializable {
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
