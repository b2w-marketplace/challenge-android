package com.danilodequeiroz.webapi.model.product

import com.google.gson.annotations.SerializedName

data class ProductsPayload(

    @field:SerializedName("offset")
	val offset: Int = 0,

    @field:SerializedName("data")
    val data: List<Product>? = null
)