package marcus.com.br.b2wtest.model.data

import com.squareup.moshi.Json

data class BannerResult(
    @field:Json(name = "data") val bannerList: List<BannerData>
)

data class BannerData(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "linkUrl") val linkUrl: String,
    @field:Json(name = "urlImagem") val urlImage: String
)