package com.mmart.sidershopping.model.entity;

public class Product {

    private Category categoria;
    private int id;
    private String nome;
    private String descricao;
    private Double precoDe;
    private Double precoPor;
    private String urlImagem;


    public Product(int id, String nome, String descricao, Double precoDe, Double precoPor, String mImage, Category category) {
        this.id = id;
        this.nome = nome;
        this.precoDe = precoDe;
        this.precoPor = precoPor;
        this.urlImagem = mImage;
        this.descricao = descricao;
    }

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

    public Double getPrecoDe() {
        return precoDe;
    }

    public void setPrecoDe(Double precoDe) {
        this.precoDe = precoDe;
    }

    public Double getPrecoPor() {
        return precoPor;
    }

    public void setPrecoPor(Double precoPor) {
        this.precoPor = precoPor;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public Category getCategoria() {
        return categoria;
    }

    public void setCategoria(Category categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
