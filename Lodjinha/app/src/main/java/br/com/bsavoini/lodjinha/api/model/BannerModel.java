package br.com.bsavoini.lodjinha.api.model;

import com.google.gson.annotations.SerializedName;

public class BannerModel {

    @SerializedName("id")
    private int id;

    @SerializedName("linkUrl")
    private String linkURL;

    @SerializedName("urlImagem")
    private String imageURL;

    public int getId() {
        return id;
    }

    public String getLinkURL() {
        return linkURL;
    }

    public String getImageURL() {
        return imageURL;
    }
}
