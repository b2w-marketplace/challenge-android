package br.com.soulblighter.lodjinha.model;

import java.util.ArrayList;
import java.util.List;

public class MaisVendidoResp {

    private List<Produto> data = new ArrayList<Produto>();

    public List<Produto> getData() {
        return data;
    }

    public void setData(List<Produto> data) {
        this.data = data;
    }
}
