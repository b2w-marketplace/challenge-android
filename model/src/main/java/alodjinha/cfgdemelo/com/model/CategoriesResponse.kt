package alodjinha.cfgdemelo.com.model

import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @SerializedName("data")
    val categories: List<Category>
)

data class Category(
    @SerializedName("descricao")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("urlImagem")
    val imageUrl: String
)