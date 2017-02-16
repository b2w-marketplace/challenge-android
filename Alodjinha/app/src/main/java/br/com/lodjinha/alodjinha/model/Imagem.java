package br.com.lodjinha.alodjinha.model;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by douglasromano on 15/02/2017.
 */

public class Imagem implements Serializable {

    private String tipo;
    private ArrayList<Bitmap> bmps;


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ArrayList<Bitmap> getBmps() {
        return bmps;
    }

    public void setBmps(ArrayList<Bitmap> bmps) {
        this.bmps = bmps;
    }
}
