package br.com.rbueno.lodjinha.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(
    @SerializedName("data")
    val categoryItem: List<CategoryItem>
) : Parcelable

@Parcelize
data class CategoryItem(
    @SerializedName("descricao")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("urlImagem")
    val urlImage: String
) : Parcelable


