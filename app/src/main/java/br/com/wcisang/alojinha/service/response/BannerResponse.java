package br.com.wcisang.alojinha.service.response;

import java.util.List;

import br.com.wcisang.alojinha.model.Banner;

/**
 * Created by WCisang on 07/06/2018.
 */
public class BannerResponse {

    List<Banner> data;

    public List<Banner> getData() {
        return data;
    }

    public void setData(List<Banner> data) {
        this.data = data;
    }
}
