package com.mmart.sidershopping.model.entity;

public class Category {

    private int id;
    private String descricao;
    private String urlImagem;

    public Category(int id, String descricao, String urlImagem) {
        this.id = id;
        this.descricao = descricao;
        this.urlImagem = urlImagem;
    }

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
