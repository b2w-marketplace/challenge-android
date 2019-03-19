package com.danilodequeiroz.webapi.model.category

import com.google.gson.annotations.SerializedName

data class ProductCategoriesPayload(

	@field:SerializedName("data")
	val data: List<ProductCategory?>? = null
)