package br.com.soulblighter.lodjinha.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Categoria implements Parcelable {

    private int id;
    private String descricao;
    private String urlImagem;

    public Categoria() {
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

    public final static Parcelable.Creator<Categoria> CREATOR = new Creator<Categoria>() {
        public Categoria createFromParcel(Parcel in) {
            return new Categoria(in);
        }

        public Categoria[] newArray(int size) {
            return (new Categoria[size]);
        }
    };

    protected Categoria(Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.descricao = ((String) in.readValue((String.class.getClassLoader())));
        this.urlImagem = ((String) in.readValue((String.class.getClassLoader())));
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(descricao);
        dest.writeValue(urlImagem);
    }

    public int describeContents() {
        return 0;
    }
}
