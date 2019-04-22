package com.desafioamedigital.util

import com.google.gson.Gson

fun <T> objectToJson(obj: T): String = Gson().toJson(obj)

inline fun <reified T> jsonToObject(json: String): T = Gson().fromJson(json, T::class.java)