package br.com.wcisang.alojinha.model;

import java.io.Serializable;

/**
 * Created by WCisang on 07/06/2018.
 */
public class Category implements Serializable {

    private int id;
    private String descricao;
    private String urlImagem;

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
