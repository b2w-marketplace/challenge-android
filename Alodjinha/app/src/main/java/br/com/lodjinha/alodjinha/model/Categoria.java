package br.com.lodjinha.alodjinha.model;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by douglasromano on 14/02/2017.
 */

public class Categoria implements Serializable {

    private String descricao;

    private String urlImagem;

    private Bitmap imgs;

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

    public Bitmap getImgs() {
        return imgs;
    }

    public void setImgs(Bitmap imgs) {
        this.imgs = imgs;
    }
}
