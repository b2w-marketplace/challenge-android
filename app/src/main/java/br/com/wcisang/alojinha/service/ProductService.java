package br.com.wcisang.alojinha.service;

import java.util.Map;

import br.com.wcisang.alojinha.service.response.ProductListResponse;
import br.com.wcisang.alojinha.service.response.ProductResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by WCisang on 07/06/2018.
 */
public interface ProductService {

    @GET("/produto/maisvendidos")
    Call<ProductResponse> getBestSellers();

    @GET("/produto")
    Call<ProductResponse> getProductListByCategoryId(@QueryMap Map<String, String> params);
}
