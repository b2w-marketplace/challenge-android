package br.com.soulblighter.lodjinha.model;

import java.util.ArrayList;
import java.util.List;

public class CategoriaResp {

    private List<Categoria> data = new ArrayList<Categoria>();

    public List<Categoria> getData() {
        return data;
    }

    public void setData(List<Categoria> data) {
        this.data = data;
    }
}
