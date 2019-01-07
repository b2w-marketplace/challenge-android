package com.b2w.lodjinha.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

data class CategoryResponse(@field:Json(name = "data") val list: List<CategoryItem>)

@Parcelize
data class CategoryItem(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "descricao") val description: String,
    @field:Json(name = "urlImagem") val image: String
) : Parcelable