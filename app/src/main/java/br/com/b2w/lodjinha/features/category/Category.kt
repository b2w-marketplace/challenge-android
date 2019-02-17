package br.com.b2w.lodjinha.features.category

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(val id: Int, @SerializedName("descricao") val description: String, val urlImagem: String) : Parcelable