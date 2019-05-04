package com.mmart.sidershopping.model.entity;

public class Banner {

    private int id;
    private String linkUrl;
    private String urlImagem;

    public Banner(int id, String linkUrl, String urlImagem) {
        this.id = id;
        this.linkUrl = linkUrl;
        this.urlImagem = urlImagem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getUlrImage() {
        return urlImagem;
    }

    public void setUlrImage(String ulrImage) {
        this.urlImagem = ulrImage;
    }
}
