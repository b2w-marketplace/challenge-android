package br.com.amedigital.challenge_android.utils

import androidx.room.TypeConverter
import br.com.amedigital.challenge_android.models.entity.Categoria
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

open class CategoriaListConverter {
    @TypeConverter
    fun fromString(value: String): List<Categoria>? {
        val listType = object : TypeToken<List<Categoria>>() {}.type
        return Gson().fromJson<List<Categoria>>(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Categoria>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}