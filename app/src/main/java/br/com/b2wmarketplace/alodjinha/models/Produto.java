package br.com.b2wmarketplace.alodjinha.models;

public class Produto {
    private int id;
    private String nome;
    private String urlimagem;
    private String descricao;
    private double precode;
    private double precopor;

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

    public String getUrlimagem() {
        return urlimagem;
    }

    public void setUrlimagem(String urlimagem) {
        this.urlimagem = urlimagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPrecode() {
        return precode;
    }

    public void setPrecode(double precode) {
        this.precode = precode;
    }

    public double getPrecopor() {
        return precopor;
    }

    public void setPrecopor(double precopor) {
        this.precopor = precopor;
    }
}
