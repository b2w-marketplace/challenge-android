package br.com.soulblighter.lodjinha.model;

import java.util.ArrayList;
import java.util.List;

public class BannerResp {

    private List<Banner> data = new ArrayList<Banner>();

    public List<Banner> getData() {
        return data;
    }

    public void setData(List<Banner> data) {
        this.data = data;
    }
}
