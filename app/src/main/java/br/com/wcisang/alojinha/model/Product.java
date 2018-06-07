package br.com.wcisang.alojinha.model;

/**
 * Created by WCisang on 07/06/2018.
 */
public class Product {

    private int id;
    private String descricao;
    private String nome;
    private float precoDe;
    private float precoPor;
    private String urlImagem;
    private Category categoria;

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
}
