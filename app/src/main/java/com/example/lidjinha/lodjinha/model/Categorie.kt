package com.example.lidjinha.lodjinha.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Categorie(val id: kotlin.Int,
                     @SerializedName("descricao") val description: String,
                     @SerializedName("urlImagem") val urlImage: String) : Serializable