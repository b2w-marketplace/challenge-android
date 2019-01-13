package b2w.com.br.olodjinha.data.api;

import java.util.Map;

import b2w.com.br.olodjinha.data.models.BannerWrapper;
import b2w.com.br.olodjinha.data.models.ProductDTO;
import b2w.com.br.olodjinha.data.models.ProductsWrapper;
import b2w.com.br.olodjinha.data.models.CategoryWrapper;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface API {

    @GET("banner")
    Observable<BannerWrapper> getBanners();

    @GET("categoria")
    Observable<CategoryWrapper> getCategories();

    @GET("produto/maisvendidos")
    Observable<ProductsWrapper> getBestSellers();

    @GET("produto")
    Observable<ProductsWrapper> getProductsByCategory(@QueryMap Map<String, Object> query);

    @GET("produto/{produtoId}")
    Observable<ProductDTO> getProductById(@Path("produtoId") Integer id);

    @POST("produto/{produtoId}")
    Observable<Object> doReservation(@Path("produtoId") Integer id);
}
