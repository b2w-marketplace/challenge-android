package com.olodjinha.guilherme.alodjinha.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Guilherme on 19/06/2018.
 */

public class Category implements Serializable {

    @SerializedName("id")
    int id;

    @SerializedName("descricao")
    String descricao;

    @SerializedName("urlImagem")
    String urlImagem;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }
}