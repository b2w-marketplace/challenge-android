package br.com.amedigital.challenge_android.repository

import androidx.lifecycle.LiveData
import br.com.amedigital.challenge_android.api.ApiResponse
import br.com.amedigital.challenge_android.api.ProdutoService
import br.com.amedigital.challenge_android.mappers.GetProdutosResponseMapper
import br.com.amedigital.challenge_android.models.Resource
import br.com.amedigital.challenge_android.models.entity.Produto
import br.com.amedigital.challenge_android.models.network.GetProdutosResponse
import br.com.amedigital.challenge_android.room.ProdutoDao
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProdutoRepository  @Inject
constructor(val produtoService: ProdutoService, val produtoDao: ProdutoDao) : Repository{

    init {
        Timber.d("Injection ProdutoRepository")
    }

    fun getProdutos(id: Int): LiveData<Resource<List<Produto>>> {
        return object : NetworkBoundRepository<List<Produto>, GetProdutosResponse, GetProdutosResponseMapper>() {
            override fun saveFetchData(items: GetProdutosResponse) {
                produtoDao.insertProdutoList(items.data)
            }

            override fun shouldFetch(data: List<Produto>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<Produto>> {
                return produtoDao.getProdutoList()
            }

            override fun fetchService(): LiveData<ApiResponse<GetProdutosResponse>> {
                return produtoService.getProdutoList(id)
            }

            override fun mapper(): GetProdutosResponseMapper {
                return GetProdutosResponseMapper()
            }

            override fun onFetchFailed(message: String?) {
                Timber.d("onFetchFailed : $message")
            }
        }.asLiveData()
    }

    fun getProdutosMaisVendidos(): LiveData<Resource<List<Produto>>> {
        return object : NetworkBoundRepository<List<Produto>, GetProdutosResponse, GetProdutosResponseMapper>() {
            override fun saveFetchData(items: GetProdutosResponse) {
                produtoDao.insertProdutoList(items.data)
            }

            override fun shouldFetch(data: List<Produto>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<Produto>> {
                return produtoDao.getProdutoList()
            }

            override fun fetchService(): LiveData<ApiResponse<GetProdutosResponse>> {
                return produtoService.getMaisVendidosList()
            }

            override fun mapper(): GetProdutosResponseMapper {
                return GetProdutosResponseMapper()
            }

            override fun onFetchFailed(message: String?) {
                Timber.d("onFetchFailed : $message")
            }
        }.asLiveData()
    }
}