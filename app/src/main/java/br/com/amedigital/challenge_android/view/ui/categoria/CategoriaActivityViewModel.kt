package br.com.amedigital.challenge_android.view.ui.categoria

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import br.com.amedigital.challenge_android.models.Resource
import br.com.amedigital.challenge_android.models.entity.Produto
import br.com.amedigital.challenge_android.repository.ProdutoRepository
import br.com.amedigital.challenge_android.utils.AbsentLiveData
import timber.log.Timber
import javax.inject.Inject

class CategoriaActivityViewModel @Inject
constructor(private val produtoRepository: ProdutoRepository
) : ViewModel(){

    private val produtoIdLiveData: MutableLiveData<Int> = MutableLiveData()
    private val produtoListLiveData: LiveData<Resource<List<Produto>>>

    init{
        Timber.d("injection CategoriaActivityViewModel")

        produtoListLiveData = Transformations.switchMap(produtoIdLiveData) {
            produtoIdLiveData.value?.let { produtoRepository.getProdutos(it) } ?: AbsentLiveData.create()
        }
    }

    fun getProdutoListObservable() = produtoListLiveData
    fun postProdutoPage(id: Int) = produtoIdLiveData.postValue(id)
}