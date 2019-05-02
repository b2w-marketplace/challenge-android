package br.com.amedigital.challenge_android.api

import androidx.lifecycle.LiveData
import br.com.amedigital.challenge_android.models.network.GetCategoriasResponse
import retrofit2.http.GET

interface CategoriaService {

    /**
     * Get categoria list
     */
    @GET("categoria")
    fun getCategoriaList(): LiveData<ApiResponse<GetCategoriasResponse>>
}