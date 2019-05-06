package br.com.douglas.fukuhara.lodjinha.network.vo;

import com.google.gson.annotations.SerializedName;

public class BannerDataVo {

    @SerializedName("id")
    private Integer id;

    @SerializedName("urlImagem")
    private String urlImagem;

    @SerializedName("linkUrl")
    private String linkUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
