package br.com.wcisang.alojinha.service.response;

import java.util.List;

import br.com.wcisang.alojinha.model.Product;

/**
 * Created by WCisang on 09/06/2018.
 */
public class ProductListResponse {

    private List<Product> data;

    private int total;

    public List<Product> getData() {
        return data;
    }

    public void setData(List<Product> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
