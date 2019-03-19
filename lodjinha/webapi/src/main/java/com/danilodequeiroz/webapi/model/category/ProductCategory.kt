package com.danilodequeiroz.webapi.model.category

import com.google.gson.annotations.SerializedName

data class ProductCategory(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("urlImagem")
	val urlImagem: String? = null,

	@field:SerializedName("descricao")
	val descricao: String? = null
)