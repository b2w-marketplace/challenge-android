package br.com.wcisang.alojinha.service;

import br.com.wcisang.alojinha.service.response.CategoryResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by WCisang on 07/06/2018.
 */
public interface CategoryService {

    @GET("/categoria")
    Call<CategoryResponse> getCategoryList();
}
