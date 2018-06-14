package br.com.soulblighter.lodjinha.model;

import java.util.ArrayList;
import java.util.List;

public class ListaProdutoResp {

    private List<Produto> data = new ArrayList<Produto>();
    private int offset;
    private int total;

    public List<Produto> getData() {
        return data;
    }

    public void setData(List<Produto> data) {
        this.data = data;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
