package br.com.b2w.lodjinha.features.product

import com.google.gson.annotations.SerializedName

data class SaveProductResponse(val result: String, @SerializedName("mensagem") val message: String)