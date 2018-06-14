package br.com.soulblighter.lodjinha.rest;

import br.com.soulblighter.lodjinha.model.BannerResp;
import br.com.soulblighter.lodjinha.model.CategoriaResp;
import br.com.soulblighter.lodjinha.model.ListaProdutoResp;
import br.com.soulblighter.lodjinha.model.MaisVendidoResp;
import br.com.soulblighter.lodjinha.model.Produto;
import br.com.soulblighter.lodjinha.model.ProdutoPostResp;
import io.reactivex.Flowable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LodjinhaService {

    @GET("banner")
    Single<BannerResp> getBanners();

    @GET("categoria")
    Single<CategoriaResp> getCategorias();

    @GET("produto")
    Single<ListaProdutoResp> getListaProdutos(@Query("offset") int offset,
                                                @Query("limit") int limit,
                                                @Query("categoriaId") int categoriaId);

    @GET("produto/maisvendidos")
    Single<MaisVendidoResp> getMaisVendido();

    @GET("produto/{produtoId}")
    Single<Produto> getProduto(@Path("produtoId") int produtoId);

    @POST("produto/{produtoId}")
    Single<ProdutoPostResp> reservarProduto(@Path("produtoId") int produtoId);
}
