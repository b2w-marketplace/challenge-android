package com.olodjinha.guilherme.alodjinha.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Guilherme on 19/06/2018.
 */

public class Banner {

    @SerializedName("id")
    int id;

    @SerializedName("urlImagem")
    String urlImagem;

    @SerializedName("linkUrl")
    String linkUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
}