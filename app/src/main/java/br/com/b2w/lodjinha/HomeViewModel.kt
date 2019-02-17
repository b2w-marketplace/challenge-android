package br.com.b2w.lodjinha

import androidx.lifecycle.*
import br.com.b2w.lodjinha.features.banner.Banner
import br.com.b2w.lodjinha.features.category.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeViewModel(private val api: Api) : ViewModel() {

    private val bannersLiveData: MutableLiveData<List<Banner>> = MutableLiveData()
    private val bannersUrlLiveData: LiveData<List<String>> = Transformations.map(bannersLiveData) {
        it.map { banner -> banner.linkUrl }
    }
    private val categoriesLiveData: MutableLiveData<List<Category>> = MutableLiveData()

    suspend fun getBannersUrl(): LiveData<List<String>> = withContext(Dispatchers.IO) {
        bannersLiveData.postValue(api.getBanners().await().data)
        bannersUrlLiveData
    }

    suspend fun getCategories(): LiveData<List<Category>> = withContext(Dispatchers.IO) {
        categoriesLiveData.apply { postValue(api.getCategories().await().data) }
    }
}