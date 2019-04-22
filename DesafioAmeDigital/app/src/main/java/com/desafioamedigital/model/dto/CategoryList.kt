package com.desafioamedigital.model.dto

import com.google.gson.annotations.SerializedName

data class CategoryList(
    @SerializedName("data")
    val categoryList: List<Category>
)

data class Category(
    @SerializedName("id")
    val id: Int,
    @SerializedName("descricao")
    val description: String,
    @SerializedName("urlImagem")
    val imageUrl: String
)