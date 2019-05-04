package com.mmart.sidershopping.model;

import com.mmart.sidershopping.model.entity.BannerResult;
import com.mmart.sidershopping.model.entity.BestSellerResult;
import com.mmart.sidershopping.model.entity.CategoriesResult;
import com.mmart.sidershopping.model.entity.Product;
import com.mmart.sidershopping.model.entity.ProductResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ALodjinhaAPI {

    @GET("banner")
    Call<BannerResult> getBanner();

    @GET("categoria")
    Call<CategoriesResult> getCategories();

    @GET("produto/maisvendidos")
    Call<BestSellerResult> getBestSellers();

    @GET("produto")
    Call<BestSellerResult> getProducts(@Query("categoriaId") int id,
                                      @Query("offset") int offset,
                                      @Query("limit") int limit);

    @GET("produto/{id}")
    Call<Product> getProduct(@Path("id") int id);

    @POST("produto/{id}")
    Call<Product> reservar(@Path("id") int id);

}
