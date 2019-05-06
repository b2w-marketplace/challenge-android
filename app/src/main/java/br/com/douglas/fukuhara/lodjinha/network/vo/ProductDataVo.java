package br.com.douglas.fukuhara.lodjinha.network.vo;

import com.google.gson.annotations.SerializedName;

public class ProductDataVo {

    @SerializedName("id")
    private Integer id;

    @SerializedName("categoria")
    private CategoryDataVo categoria;

    @SerializedName("descricao")
    private String descricao;

    @SerializedName("nome")
    private String nome;

    @SerializedName("urlImagem")
    private String urlImagem;

    @SerializedName("precoDe")
    private Double precoDe;

    @SerializedName("precoPor")
    private Double precoPor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CategoryDataVo getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoryDataVo categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
}
