package br.com.rbueno.lodjinha.model

import com.google.gson.annotations.SerializedName

data class Banner(
    @SerializedName("data") val bannerItem: List<BannerItem>
)

data class BannerItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("linkUrl")
    val linkUrl: String,
    @SerializedName("urlImagem")
    val urlImage: String
)