package com.example.lidjinha.lodjinha.model

import com.google.gson.annotations.SerializedName

data class Product(val id: kotlin.Int,
                   @SerializedName("nome") val name: String,
                   @SerializedName("urlImagem") val urlImage: String,
                   @SerializedName("descricao") val description: String,
                   @SerializedName("precoDe") val fullPrice: Double,
                   @SerializedName("precoPor") val salePrice: Double,
                   @SerializedName("categoria") val category: Categorie)