package br.com.douglas.fukuhara.lodjinha.network;

import br.com.douglas.fukuhara.lodjinha.network.vo.BannerVo;
import br.com.douglas.fukuhara.lodjinha.network.vo.CategoryVo;
import br.com.douglas.fukuhara.lodjinha.network.vo.ProductBestSellerVo;
import br.com.douglas.fukuhara.lodjinha.network.vo.ProductDataVo;
import br.com.douglas.fukuhara.lodjinha.network.vo.ProductVo;
import br.com.douglas.fukuhara.lodjinha.network.vo.ResultVo;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestApi {

    /*
        ENDPOINT DEFINITION
     */
    // BANNER endpoint
    String BANNER = "/banner";

    // CATEGORY endpoint
    String CATEGORIA = "/categoria";

    // PRODUCT endpoint
    String PRODUTO = "/produto";
    String PRODUTO_MAIS_VENDIDO = PRODUTO + "/maisvendidos";
    String PRODUTO_ID_PATH = "produtoId";
    String PRODUTO_ID = "/{" + PRODUTO_ID_PATH + "}";

    /*
        REQUEST METHODS
     */
    @GET(BANNER)
    Observable<BannerVo> getBanner();

    @GET(CATEGORIA)
    Observable<CategoryVo> getCategoria();

    @GET(PRODUTO)
    Observable<ProductVo> getProduto(
            @Query("offset") Integer offset,
            @Query("limit") Integer limit,
            @Query("categoriaId") Integer categoriaId);

    @GET(PRODUTO_MAIS_VENDIDO)
    Observable<ProductBestSellerVo> getProdutoMaisVendido();

    @GET(PRODUTO + PRODUTO_ID)
    Observable<ProductDataVo> getProductId(@Path(PRODUTO_ID_PATH) String produtoId);

    @POST(PRODUTO + PRODUTO_ID)
    Observable<ResultVo> reserveProductId(@Path(PRODUTO_ID_PATH) String produtoId);
}
