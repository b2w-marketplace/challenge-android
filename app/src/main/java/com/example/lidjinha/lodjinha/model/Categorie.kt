package com.example.lidjinha.lodjinha.model

import com.google.gson.annotations.SerializedName

data class Categorie(val id: kotlin.Int,
                     @SerializedName("descricao") val description: String,
                     @SerializedName("urlImagem") val urlImage: String)