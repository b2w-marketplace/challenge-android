package com.abmm.b2w.alodjinha.http_module;

import com.abmm.b2w.alodjinha.model.Banner;
import com.abmm.b2w.alodjinha.model.Product;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ILodjinhaApi {

    @GET("/banner")
    Call<Envelope<Banner>> getBanners();

    @GET("/produto")
    Call<Envelope<Product>> getProducts();
}
