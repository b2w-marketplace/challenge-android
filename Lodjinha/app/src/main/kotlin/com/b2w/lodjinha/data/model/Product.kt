package com.b2w.lodjinha.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

data class ProductsResponse(@field:Json(name = "data") val list: MutableList<ProductItem>)

@Parcelize
data class ProductItem(
    @field:Json(name = "categoria") val category: CategoryItem,
    @field:Json(name = "descricao") val description: String,
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "nome") val name: String,
    @field:Json(name = "precoDe") val fromPrice: Double,
    @field:Json(name = "precoPor") val toPrice: Double,
    @field:Json(name = "urlImagem") val image: String) : Parcelable

data class BestSellingResponse(@field:Json(name = "data") val list: MutableList<ProductItem>)