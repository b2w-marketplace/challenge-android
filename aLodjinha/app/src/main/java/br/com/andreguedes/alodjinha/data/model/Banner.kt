package br.com.andreguedes.alodjinha.data.model

import com.google.gson.annotations.SerializedName

data class Banner(
        @field:SerializedName("id") val id: Int? = null,
        @field:SerializedName("urlImagem") val urlImagem: String? = null,
        @field:SerializedName("linkUrl") val linkUrl: String? = null
)