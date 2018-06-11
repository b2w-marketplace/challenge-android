package com.example.lidjinha.lodjinha.data.model

import com.google.gson.annotations.SerializedName

data class WSResponse<out T>(@SerializedName("data") val body: T?)