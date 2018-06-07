package br.com.wcisang.alojinha.service;

import br.com.wcisang.alojinha.service.response.ProductResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by WCisang on 07/06/2018.
 */
public interface ProductService {

    @GET("/produto/maisvendidos")
    Call<ProductResponse> getBestSellers();
}
