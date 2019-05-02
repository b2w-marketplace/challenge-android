package br.com.amedigital.challenge_android.view.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import br.com.amedigital.challenge_android.models.Resource
import br.com.amedigital.challenge_android.models.entity.Banner
import br.com.amedigital.challenge_android.models.entity.Categoria
import br.com.amedigital.challenge_android.models.entity.Produto
import br.com.amedigital.challenge_android.repository.BannerRepository
import br.com.amedigital.challenge_android.repository.CategoriaRepository
import br.com.amedigital.challenge_android.repository.ProdutoRepository
import br.com.amedigital.challenge_android.utils.AbsentLiveData
import timber.log.Timber
import javax.inject.Inject

class HomeActivityViewModel @Inject
constructor(
    private val bannerRepository: BannerRepository,
    private val categoriaRepository: CategoriaRepository,
    private val produtoRepository: ProdutoRepository
) : ViewModel() {

    private val bannerIdLiveData: MutableLiveData<Int> = MutableLiveData()
    private val bannerListLiveData: LiveData<Resource<List<Banner>>>

    private val categoriaIdLiveData: MutableLiveData<Int> = MutableLiveData()
    private val categoriaListLiveData: LiveData<Resource<List<Categoria>>>

    private val maisVendidosIdLiveData: MutableLiveData<Int> = MutableLiveData()
    private val maisVendidosListLiveData: LiveData<Resource<List<Produto>>>

    init {
        Timber.d("injection HomeActivityViewModel")

        bannerListLiveData = Transformations.switchMap(bannerIdLiveData) {
            bannerIdLiveData.value?.let { bannerRepository.getBanners() } ?: AbsentLiveData.create()
        }

        categoriaListLiveData = Transformations.switchMap(categoriaIdLiveData) {
            categoriaIdLiveData.value?.let { categoriaRepository.getCategorias() } ?: AbsentLiveData.create()
        }

        maisVendidosListLiveData = Transformations.switchMap(maisVendidosIdLiveData) {
            maisVendidosIdLiveData.value?.let { produtoRepository.getProdutosMaisVendidos() } ?: AbsentLiveData.create()
        }
    }

    fun getBannerListObservable() = bannerListLiveData
    fun postBannerPage(id: Int) = bannerIdLiveData.postValue(id)

    fun getCategoriaListObservable() = categoriaListLiveData
    fun postCategoriaPage(id: Int) = categoriaIdLiveData.postValue(id)

    fun getMaisVendidosListObservable() = maisVendidosListLiveData
    fun postMaisVendidosPage(id: Int) = maisVendidosIdLiveData.postValue(id)
}