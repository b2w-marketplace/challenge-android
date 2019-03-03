package br.com.andrecouto.alodjinha.ui.fragment.home

import android.util.Log
import br.com.andrecouto.alodjinha.domain.model.common.Status
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Banner
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Category
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Product
import br.com.andrecouto.alodjinha.domain.model.response.ErrorResponse
import br.com.andrecouto.alodjinha.domain.model.response.SuccessResponse
import br.com.andrecouto.alodjinha.domain.model.response.UseCaseResponse
import br.com.andrecouto.alodjinha.domain.usecase.banner.GetBanners
import br.com.andrecouto.alodjinha.domain.usecase.category.GetCategories
import br.com.andrecouto.alodjinha.domain.usecase.product.GetTopSellingProducts
import br.com.andrecouto.alodjinha.ui.base.BaseViewModel
import br.com.andrecouto.alodjinha.ui.fragment.home.adapter.OnBannerSelected
import br.com.andrecouto.alodjinha.util.arch.SingleLiveEvent
import br.com.andrecouto.alodjinha.util.connectivity.BaseConnectionManager
import br.com.andrecouto.alodjinha.util.livedata.NonNullLiveData
import javax.inject.Inject
import kotlin.concurrent.thread

class HomeViewModel @Inject constructor(
        connectionManager: BaseConnectionManager,
        private val getBannersUseCase: GetBanners,
        private val getCategoriasUseCase: GetCategories,
        private val getTopSellingProductsUseCase: GetTopSellingProducts
) : BaseViewModel(connectionManager), OnBannerSelected {

    private val TAG = HomeViewModel::class.java.simpleName

    val banners = NonNullLiveData<List<Banner>>(emptyList())
    val categories = NonNullLiveData<List<Category>>(emptyList())
    val products = NonNullLiveData<List<Product>>(emptyList())
    val selectedCategory = NonNullLiveData(Category(0,"",""))
    val selectedProduct = NonNullLiveData(Product(0,"", "", 0.0f, 0.0f, ""))

    val statusBannner = SingleLiveEvent(Status.EMPTY)
    val statusCategory = SingleLiveEvent(Status.EMPTY)
    val statusProducts = SingleLiveEvent(Status.EMPTY)



    fun init() {
        selectedCategory.value = Category(0,"","")
        selectedProduct.value = Product(0,"", "", 0.0f, 0.0f, "")
        if (checkConnection()) {
            statusBannner.value = Status.LOADING
            statusCategory.value = Status.LOADING
            statusProducts.value = Status.LOADING
            getBannersUseCase
                    .execute(compositeDisposable, this::getAllBanners, null)
            getCategoriasUseCase
                    .execute(compositeDisposable, this::getAllCategories, null)
            getTopSellingProductsUseCase
                    .execute(compositeDisposable, this::getTopSellingProducts, null)
        } else {
            statusBannner.value = Status.NO_CONNECTION
            statusCategory.value = Status.NO_CONNECTION
            statusProducts.value = Status.NO_CONNECTION
        }

    }

    private fun getAllBanners(response: UseCaseResponse<List<Banner>>) {
        Log.d(TAG, "getAllBanners() called  with: response = [$response]")

        when (response) {
            is SuccessResponse -> {
                statusBannner.value = Status.LOADED
                banners.value = response.value
            }
            is ErrorResponse -> statusBannner.value = Status.FAILED
        }
    }

    private fun getAllCategories(response: UseCaseResponse<List<Category>>) {
        Log.d(TAG, "getAllCategories() called  with: response = [$response]")
        when (response) {
            is SuccessResponse -> {
                statusCategory.value = Status.LOADED
                categories.value = response.value
            }
            is ErrorResponse -> statusCategory.value = Status.FAILED
        }
    }

    private fun getTopSellingProducts(response: UseCaseResponse<List<Product>>) {
        Log.d(TAG, "getTopSellingProducts() called  with: response = [$response]")
        when (response) {
            is SuccessResponse -> {
                statusProducts.value = Status.LOADED
                products.value = response.value
            }
            is ErrorResponse -> statusProducts.value = Status.FAILED
        }
    }

    fun onCategoryClicked(category: Category) {
        Log.d(TAG, "onCategoryClicked() called  with: response = [$category]")
        selectedCategory.value = category
    }

    fun onProductClicked(product: Product) {
        Log.d(TAG, "onProdcutClicked() called  with: response = [$product]")
        selectedProduct.value = product
    }

    override fun onBannerSelected(position: Int) {
        Log.d(TAG, "onBannerSelected() called  with: response = [${banners.value.get(position)}]")
    }

}