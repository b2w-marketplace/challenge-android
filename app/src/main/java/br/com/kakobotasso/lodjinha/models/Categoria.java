package br.com.kakobotasso.lodjinha.models;

import com.google.gson.annotations.SerializedName;

public class Categoria {
    @SerializedName("id")
    int id;

    @SerializedName("descricao")
    String descricao;

    @SerializedName("urlImagem")
    String imagem;

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

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
