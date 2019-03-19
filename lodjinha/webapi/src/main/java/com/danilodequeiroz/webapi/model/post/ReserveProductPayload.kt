package com.danilodequeiroz.webapi.model.post

import com.google.gson.annotations.SerializedName

data class ReserveProductPayload(

	@field:SerializedName("result")
	val result: String? = null,

	@field:SerializedName("mensagem")
	val mensagem: String? = null
)