package br.com.rbueno.lodjinha.model


import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("data")
    val categoryItem: List<CategoryItem>
)


data class CategoryItem(
    @SerializedName("descricao")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("urlImagem")
    val urlImage: String
)


