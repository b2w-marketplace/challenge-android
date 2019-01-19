package br.com.cemobile.domain.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    @SerializedName("id") val id: Long,
    @SerializedName("nome") val name: String,
    @SerializedName("urlImagem") val imageUrl: String,
    @SerializedName("descricao") val description: String,
    @SerializedName("precoDe") val fromPrice: Float,
    @SerializedName("precoPor") val byPrice: Float,
    @SerializedName("categoria") val category: Category,
    @Expose(serialize = false, deserialize = false) var bestSelling: Boolean = false
) : Parcelable