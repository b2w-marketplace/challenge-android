package com.danilodequeiroz.webapi.model.banner

import com.google.gson.annotations.SerializedName

data class Banner(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("linkUrl")
	val linkUrl: String? = null,


	@field:SerializedName("urlImagem")
	val urlImagem: String? = null
)