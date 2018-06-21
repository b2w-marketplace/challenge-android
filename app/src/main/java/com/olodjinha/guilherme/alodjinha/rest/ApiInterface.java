package com.olodjinha.guilherme.alodjinha.rest;

import com.olodjinha.guilherme.alodjinha.model.Data;
import com.olodjinha.guilherme.alodjinha.model.DataCategory;
import com.olodjinha.guilherme.alodjinha.model.DataProduct;
import com.olodjinha.guilherme.alodjinha.model.Product;
import com.olodjinha.guilherme.alodjinha.model.ResultStatus;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Guilherme on 19/06/2018.
 */

public interface ApiInterface {

    @GET("banner")
    Call<Data> getDataBanner();

    @GET("categoria")
    Call<DataCategory> getCategory();

    @GET("produto")
    Call<DataProduct> getProduct(@Query("offset") int offset
            , @Query("limit") int limit
            , @Query("categoriaId") int categoriaId
    );

    @GET("produto/maisvendidos")
    Call<DataProduct> getBestSellers();

    @GET("produto/{produtoId}")
    Call<Product> getProductById(@Path("produtoId") int produtoId);

    @POST("produto/{produtoId}")
    Call<ResultStatus> postBookProduct(@Path("produtoId") int produtoId);
}