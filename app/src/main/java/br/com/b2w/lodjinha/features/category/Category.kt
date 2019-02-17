package br.com.b2w.lodjinha.features.category

import com.google.gson.annotations.SerializedName

data class Category(val id: Int, @SerializedName("descricao") val description: String, val urlImagem: String)