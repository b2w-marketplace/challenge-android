package com.desafioamedigital.model.dto

import com.google.gson.annotations.SerializedName

data class BannerList(
    @SerializedName("data")
    val bannerList: List<Banner>
)

data class Banner(
    @SerializedName("id")
    val id: Int,
    @SerializedName("linkUrl")
    val linkUrl: String,
    @SerializedName("urlImagem")
    val imageUrl: String
)