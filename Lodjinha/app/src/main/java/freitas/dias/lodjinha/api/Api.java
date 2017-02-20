package freitas.dias.lodjinha.api;

import freitas.dias.lodjinha.api.model.Banners;
import freitas.dias.lodjinha.api.model.Categories;
import freitas.dias.lodjinha.api.model.Product;
import freitas.dias.lodjinha.api.model.Products;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @GET("banner")
    Call<Banners> getListBanners();

    @GET("categoria")
    Call<Categories> getListCategories();

    @GET("produto")
    Call<Product> getListProducts(@Query("categoriaId") int categoryId);

    @GET("produto/maisvendidos")
    Call<Products> getListProductsSold();

    @GET("produto/{produtoId}")
    Call<Product> getProduct(@Path("produtoId") int produtoId);

    @POST("produto/{produtoId}")
    Call<Product> reserveProduct(@Path("produtoId") int produtoId);
}
