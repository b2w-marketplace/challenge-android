package alodjinha.cfgdemelo.com.model

import com.google.gson.annotations.SerializedName

object BannersResponse {
    @SerializedName("data")
    var banners: Array<BannerContent>? = null
}

object BannerContent {
    @SerializedName("id")
    var id: Int? = null
    @SerializedName("urlImagem")
    var urlImagem: String? = null
    @SerializedName("linkUrl")
    var linkUrl: String? = null
}