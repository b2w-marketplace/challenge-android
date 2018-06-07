package br.com.wcisang.alojinha.service.response;

import java.util.List;

import br.com.wcisang.alojinha.model.Category;

/**
 * Created by WCisang on 07/06/2018.
 */
public class CategoryResponse {

    List<Category> data;

    public List<Category> getData() {
        return data;
    }

    public void setData(List<Category> data) {
        this.data = data;
    }
}
