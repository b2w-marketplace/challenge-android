package com.b2w.lodjinha.data.model

import com.squareup.moshi.Json

data class BannerResponse(@field:Json(name = "data") val list: List<BannerItem>)

data class BannerItem(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "urlImagem") val image: String,
    @field:Json(name = "linkUrl") val link: String
)