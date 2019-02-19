package br.com.b2w.lodjinha.views

import androidx.lifecycle.*
import br.com.b2w.lodjinha.Api
import br.com.b2w.lodjinha.features.banner.Banner
import br.com.b2w.lodjinha.features.category.Category
import br.com.b2w.lodjinha.features.product.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeViewModel(private val api: Api) : ViewModel() {

    val homeLiveData: MutableLiveData<Home> = MutableLiveData()

    suspend fun getHomeData() = withContext(Dispatchers.IO) {
        val banners = api.getBanners().await().data
        val categories = api.getCategories().await().data
        val products = api.getBestSellerProducts().await().data
        homeLiveData.postValue(Home(banners, categories, products))
    }

    data class Home(val banners: List<Banner>, val categories: List<Category>, val products: List<Product>)
}