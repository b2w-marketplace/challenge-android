package br.com.amedigital.challenge_android.repository

import androidx.lifecycle.LiveData
import br.com.amedigital.challenge_android.api.ApiResponse
import br.com.amedigital.challenge_android.api.CategoriaService
import br.com.amedigital.challenge_android.mappers.GetCategoriasResponseMapper
import br.com.amedigital.challenge_android.models.Resource
import br.com.amedigital.challenge_android.models.entity.Categoria
import br.com.amedigital.challenge_android.models.network.GetCategoriasResponse
import br.com.amedigital.challenge_android.room.CategoriaDao
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoriaRepository  @Inject
constructor(val categoriaService: CategoriaService, val categoriaDao: CategoriaDao)
    : Repository {

    init {
        Timber.d("Injection CategoriaRepository")
    }

    fun getCategorias(): LiveData<Resource<List<Categoria>>> {
        return object : NetworkBoundRepository<List<Categoria>, GetCategoriasResponse, GetCategoriasResponseMapper>() {
            override fun saveFetchData(items: GetCategoriasResponse) {
                categoriaDao.insertCategoriaList(items.data)
            }

            override fun shouldFetch(data: List<Categoria>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<Categoria>> {
                return categoriaDao.getCategoriaList()
            }

            override fun fetchService(): LiveData<ApiResponse<GetCategoriasResponse>> {
                return categoriaService.getCategoriaList()
            }

            override fun mapper(): GetCategoriasResponseMapper {
                return GetCategoriasResponseMapper()
            }

            override fun onFetchFailed(message: String?) {
                Timber.d("onFetchFailed : $message")
            }
        }.asLiveData()
    }
}