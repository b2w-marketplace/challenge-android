package alodjinha.cfgdemelo.com.model

import com.google.gson.annotations.SerializedName

data class BookingResponse(
    @SerializedName("result")
    var result: String
)