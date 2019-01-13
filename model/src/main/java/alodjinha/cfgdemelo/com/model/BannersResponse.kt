package alodjinha.cfgdemelo.com.model

import com.google.gson.annotations.SerializedName

data class BannersResponse (
    @SerializedName("data")
    var banners: List<BannerContent>
)

data class BannerContent (
    @SerializedName("id")
    var id: Int,
    @SerializedName("linkUrl")
    var linkUrl: String,
    @SerializedName("urlImagem")
    var imageUrl: String
)