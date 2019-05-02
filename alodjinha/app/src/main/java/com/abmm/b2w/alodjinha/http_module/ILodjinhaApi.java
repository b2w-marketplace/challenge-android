package com.abmm.b2w.alodjinha.http_module;

import com.abmm.b2w.alodjinha.model.Banner;
import com.abmm.b2w.alodjinha.model.Category;
import com.abmm.b2w.alodjinha.model.Product;

import retrofit2.Call;
import retrofit2.http.GET;
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
}
