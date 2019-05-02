package br.com.amedigital.challenge_android.api

import androidx.lifecycle.LiveData
import br.com.amedigital.challenge_android.models.network.GetProdutosResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ProdutoService {

    /**
     * Get produto list
     */
    @GET("produto")
    fun getProdutoList(@Query("categoriaId") id: Int): LiveData<ApiResponse<GetProdutosResponse>>


    /**
     * Get mais vendidos list
     */
    @GET("produto/maisvendidos")
    fun getMaisVendidosList(): LiveData<ApiResponse<GetProdutosResponse>>
}