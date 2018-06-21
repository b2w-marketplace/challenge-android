package com.olodjinha.guilherme.alodjinha.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Guilherme on 19/06/2018.
 */

public class Product implements Serializable {

    @SerializedName("id")
    int id;

    @SerializedName("nome")
    String nome;

    @SerializedName("urlImagem")
    String urlImagem;

    @SerializedName("descricao")
    String descricao;

    @SerializedName("precoDe")
    float precoDe;

    @SerializedName("precoPor")
    float precoPor;

    @SerializedName("categoria")
    Category category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getPrecoDe() {
        return precoDe;
    }

    public void setPrecoDe(float precoDe) {
        this.precoDe = precoDe;
    }

    public float getPrecoPor() {
        return precoPor;
    }

    public void setPrecoPor(float precoPor) {
        this.precoPor = precoPor;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}