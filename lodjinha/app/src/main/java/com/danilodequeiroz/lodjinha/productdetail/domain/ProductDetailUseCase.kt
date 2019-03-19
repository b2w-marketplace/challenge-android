package com.danilodequeiroz.lodjinha.productdetail.domain

import android.os.Parcelable
import com.danilodequeiroz.lodjinha.home.domain.BaseViewModel
import com.danilodequeiroz.webapi.model.post.ReserveProductPayload
import io.reactivex.Single
import kotlinx.android.parcel.Parcelize

interface ProductDetailUseCase {
    fun getProduct(productId: Int): Single<DetailedProducViewModel>
    fun reserveProduct(productId: Int): Single<ReserveProductPayload>
}

@Parcelize
data class DetailedProducViewModel (

        val id: Int? = null,
        val categoriaId: Int? = null,
        val categoriaName: String? = null,
        val nome: String? = null,
        val desc: String? = null,
        val precoDe: String? = null,
        val precoPor: String? = null,
        val urlImagem: String? = null

) : BaseViewModel() , Parcelable


