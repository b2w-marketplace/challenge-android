package br.com.b2w.desafio.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Categoria (
    @SerializedName("id") val id: Int,
    @SerializedName("descricao") val descricao: String,
    @SerializedName("urlImagem")val urlImagem: String
) : Parcelable