package com.danilodequeiroz.lodjinha.home.domain

import android.os.Parcelable
import io.reactivex.Single
import kotlinx.android.parcel.Parcelize

interface HomeUseCase {
    fun getAllBestSellingProducts(): Single<MutableList<ProductViewModel>>
    fun getProductCategories(): Single<MutableList<ProductCategoryViewModel>>
    fun getBanners(): Single<MutableList<BannerViewModel>>
}

@Parcelize
data class BannerViewModel(
    val linkUrl: String? = null,
    val urlImagem: String? = null
) : BaseViewModel() , Parcelable

@Parcelize
data class ProductCategoryViewModel(
    val id: Int? = null,
    val descricao: String? = null,
    val urlImagem: String? = null
) : BaseViewModel() , Parcelable

@Parcelize
data class ProductViewModel (

    val id: Int? = null,
    val categoriaId: Int? = null,
    val nome: String? = null,
    val precoDe: String? = null,
    val precoPor: String? = null,
    val urlImagem: String? = null

) : BaseViewModel() , Parcelable

open class BaseViewModel