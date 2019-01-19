package br.com.cemobile.data.remote.models

import br.com.cemobile.domain.model.Product
import com.google.gson.annotations.SerializedName

data class ProductsResponse(
    @SerializedName("data") val data: List<Product>,
    @SerializedName("offset") val offset: Int,
    @SerializedName("total") val total: Int
)