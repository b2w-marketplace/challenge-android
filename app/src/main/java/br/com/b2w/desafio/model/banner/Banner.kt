package br.com.b2w.desafio.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Banner (
    @SerializedName("id") val id: Int,
    @SerializedName("urlImagem") val urlImagem: String,
    @SerializedName("linkUrl")val linkUrl: String
) : Parcelable