package br.com.cemobile.data.repository

import br.com.cemobile.data.local.errors.HomeDataException
import br.com.cemobile.domain.FetchStrategy
import br.com.cemobile.domain.FetchStrategy.ForceUpdate
import br.com.cemobile.domain.model.*
import br.com.cemobile.domain.repository.BannerRepository
import br.com.cemobile.domain.repository.CategoryRepository
import br.com.cemobile.domain.repository.HomeDataRepository
import br.com.cemobile.domain.repository.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlin.coroutines.CoroutineContext

class HomeDataRepositoryImpl(
    private val bannerRepository: BannerRepository,
    private val categoryRepository: CategoryRepository,
    private val productRepository: ProductRepository,
    ioContext: CoroutineContext = Dispatchers.IO
) : HomeDataRepository {

    private val ioScope = CoroutineScope(ioContext)

    override suspend fun getHomeDataInformation(strategy: FetchStrategy): Result<HomeData> =
        try {
            val banners = getBanners()
            val categories = getCategories()
            val bestSellingProducts = getBestSellingProducts()
            Result.Success(HomeData(banners, categories, bestSellingProducts))
        } catch (error: Throwable) {
            Result.Error(error)
        }

    private suspend fun getBanners(): List<Banner> {
        val result = ioScope.async { bannerRepository.getAllBanners(ForceUpdate) }.await()
        return when (result) {
            is Result.Success -> result.data
            else -> throw HomeDataException()
        }
    }

    private suspend fun getCategories(): List<Category> {
        val result = ioScope.async { categoryRepository.getCategories(ForceUpdate) }.await()
        return when (result) {
            is Result.Success -> result.data
            else -> throw HomeDataException()
        }
    }

    private suspend fun getBestSellingProducts(): List<Product> {
        val result = ioScope.async { productRepository.getBestSellingProducts(ForceUpdate) }.await()
        return when (result) {
            is Result.Success -> result.data
            else -> throw HomeDataException()
        }
    }


}