package alodjinha.cfgdemelo.com.model

import com.google.gson.annotations.SerializedName

data class BestSellersResponse(
    @SerializedName("data")
    val bestSellers: List<BestSeller>
)

data class BestSeller(
    @SerializedName("categoria")
    val category: BestSellersCategory,
    @SerializedName("descricao")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("nome")
    val name: String,
    @SerializedName("precoDe")
    val priceFrom: Int,
    @SerializedName("precoPor")
    val priceFor: Double,
    @SerializedName("urlImagem")
    val imageUrl: String
)

data class BestSellersCategory(
    @SerializedName("descricao")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("urlImagem")
    val imageUrl: String
)