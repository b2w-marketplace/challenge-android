package br.com.cemobile.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(
    @SerializedName("id") val id: Long,
    @SerializedName("descricao") val description: String,
    @SerializedName("urlImagem") val imageUrl: String
) : Parcelable