package com.danilodequeiroz.webapi.model.product

import com.danilodequeiroz.webapi.model.category.ProductCategory
import com.google.gson.annotations.SerializedName

data class Product(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("categoria")
	val categoria: ProductCategory? = null,

	@field:SerializedName("nome")
	val nome: String? = null,

	@field:SerializedName("precoDe")
	val precoDe: Int? = null,

	@field:SerializedName("precoPor")
	val precoPor: Double? = null,

	@field:SerializedName("descricao")
	val descricao: String? = null,

	@field:SerializedName("urlImagem")
	val urlImagem: String? = null

)