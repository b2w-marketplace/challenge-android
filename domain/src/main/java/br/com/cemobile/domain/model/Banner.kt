package br.com.cemobile.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Banner(
    @SerializedName("id") val id: Long,
    @SerializedName("urlImagem") val imageUrl: String,
    @SerializedName("linkUrl") val linkUrl: String
) : Parcelable