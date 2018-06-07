package br.com.wcisang.alojinha.service.response;

import java.util.List;

import br.com.wcisang.alojinha.model.Product;

/**
 * Created by WCisang on 07/06/2018.
 */
public class ProductResponse {

    private List<Product> data;

    public List<Product> getData() {
        return data;
    }

    public void setData(List<Product> data) {
        this.data = data;
    }
}
