package com.danilodequeiroz.webapi.model.banner

import com.google.gson.annotations.SerializedName

data class BannersPayload(

	@field:SerializedName("data")
	val data: List<Banner?>? = null
)