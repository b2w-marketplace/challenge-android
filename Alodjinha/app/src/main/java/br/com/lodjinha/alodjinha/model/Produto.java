package br.com.lodjinha.alodjinha.model;

import android.graphics.Bitmap;
import android.text.Spanned;

import java.io.Serializable;
import java.util.List;

/**
 * Created by douglasromano on 15/02/2017.
 */

public class Produto implements Serializable {

    private int id;

    private String nome;

    private String urlImagem;

    private Spanned descricao;

    private double precoDe;

    private double precoPor;

    private Bitmap imgProduto;

    private List<Categoria> categorias;

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

    public Spanned getDescricao() {
        return descricao;
    }

    public void setDescricao(Spanned descricao) {
        this.descricao = descricao;
    }

    public double getPrecoDe() {
        return precoDe;
    }

    public void setPrecoDe(double precoDe) {
        this.precoDe = precoDe;
    }

    public double getPrecoPor() {
        return precoPor;
    }

    public void setPrecoPor(double precoPor) {
        this.precoPor = precoPor;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Bitmap getImgProduto() {
        return imgProduto;
    }

    public void setImgProduto(Bitmap imgProduto) {
        this.imgProduto = imgProduto;
    }
}
