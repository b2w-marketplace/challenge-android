package br.com.amedigital.challenge_android.utils

import androidx.room.TypeConverter
import br.com.amedigital.challenge_android.models.entity.Categoria
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

open class CategoriaConverter {
    @TypeConverter
    fun fromString(value: String): Categoria? {
        val listType = object : TypeToken<Categoria>() {}.type
        return Gson().fromJson<Categoria>(value, listType)
    }

    @TypeConverter
    fun fromCategoria(list: Categoria?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}