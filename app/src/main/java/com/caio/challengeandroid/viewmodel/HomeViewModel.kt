package com.caio.lodjinha.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.caio.lodjinha.repository.BannerRepository
import com.caio.lodjinha.repository.CategoryRepository
import com.caio.lodjinha.repository.ProductRepository
import com.caio.lodjinha.repository.entity.Banner
import com.caio.lodjinha.repository.entity.Category
import com.caio.lodjinha.repository.entity.Product

class HomeViewModel(
    private val bannerRepository: BannerRepository,
    private val categoryRepository: CategoryRepository,
    private val productRepository: ProductRepository) : ViewModel() {

    private val TAG : String = "HomeViewModel"

    val homeLiveData: MutableLiveData<Home> = MutableLiveData()

    suspend fun loadHomeData(){
        val banner = bannerRepository.getBanner()
        Log.d(TAG, "banner->"+banner.size.toString())

        val category = categoryRepository.getCategory()
        Log.d(TAG, "category->"+category.size.toString())

        val product = productRepository.getProductsMoreSallers()
        Log.d(TAG, "product->"+product.size.toString())

        homeLiveData.postValue(Home(banner,category,product))
    }


    data class Home(val banners: List<Banner>, val categories: List<Category>, val products: List<Product>)
}