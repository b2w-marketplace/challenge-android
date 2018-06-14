package br.com.soulblighter.lodjinha.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Produto implements Parcelable {

    private Categoria categoria;
    private String descricao;
    private int id;
    private String nome;
    private float precoDe;
    private float precoPor;
    private String urlImagem;

    public Produto() {
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
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


    public final static Parcelable.Creator<Produto> CREATOR = new Creator<Produto>() {
        public Produto createFromParcel(Parcel in) {
            return new Produto(in);
        }

        public Produto[] newArray(int size) {
            return (new Produto[size]);
        }
    };

    protected Produto(Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.nome = ((String) in.readValue((String.class.getClassLoader())));
        this.urlImagem = ((String) in.readValue((String.class.getClassLoader())));
        this.descricao = ((String) in.readValue((String.class.getClassLoader())));
        this.precoDe = ((float) in.readValue((float.class.getClassLoader())));
        this.precoPor = ((float) in.readValue((float.class.getClassLoader())));
        this.categoria = ((Categoria) in.readValue((Categoria.class.getClassLoader())));
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(nome);
        dest.writeValue(urlImagem);
        dest.writeValue(descricao);
        dest.writeValue(precoDe);
        dest.writeValue(precoPor);
        dest.writeValue(categoria);
    }

    public int describeContents() {
        return 0;
    }
}
