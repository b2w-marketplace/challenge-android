package br.com.bsavoini.lodjinha.api;

import br.com.bsavoini.lodjinha.api.model.*;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LodjinhaService {

    @GET("banner")
    Call<BannersResponse> requestBanner();

    @GET("categoria")
    Call<CategoriesResponse> requestCategories();

    @GET("produto")
    Call<ProductsResponse> requestProducts(@Query("offset") int offset, @Query("limit") int limit, @Query("categoriaId") int categoryId);

    @GET("produto/maisvendidos")
    Call<ProductsResponse> requestBestSellers();

    @GET("produto/{produtoId}")
    Call<ProductModel> requestProduct(@Path(value = "produtoId") int productId);

    @POST("produto/{produtoId}")
    Call<ReservationResponse> requestProductReservation(@Path(value = "produtoId") int productId);

}
