package com.abmm.b2w.alodjinha.http_module;

import com.abmm.b2w.alodjinha.model.Banner;
import com.abmm.b2w.alodjinha.model.Category;
import com.abmm.b2w.alodjinha.model.ChartMessage;
import com.abmm.b2w.alodjinha.model.Product;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ILodjinhaApi {

    @GET("/banner")
    Call<Envelope<Banner>> getBanners();

    @GET("/categoria")
    Call<Envelope<Category>> getCategories();

    @GET("/produto/maisvendidos")
    Call<Envelope<Product>> getTopSeller();

    @GET("/produto")
    Call<Envelope<Product>> getProducts(@Query("offset") int offset, @Query("limit") int limit, @Query("categoriaId") int categoryId);

    @GET("/produto/{id}")
    Call<Product> getProductById(@Path("id") int id);

    @POST("/produto/{id}")
    Call<ChartMessage> addProductToChart(@Path("id") int id);
}
