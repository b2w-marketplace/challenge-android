package com.danilodequeiroz.webapi.model.product

import com.google.gson.annotations.SerializedName

data class BestSellingProductsPayload(

	@field:SerializedName("data")
	val data: List<Product>? = null
)