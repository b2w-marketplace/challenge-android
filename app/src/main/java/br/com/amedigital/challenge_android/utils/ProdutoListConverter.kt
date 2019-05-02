package br.com.amedigital.challenge_android.utils

import androidx.room.TypeConverter
import br.com.amedigital.challenge_android.models.entity.Produto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

open class ProdutoListConverter {
    @TypeConverter
    fun fromString(value: String): List<Produto>? {
        val listType = object : TypeToken<List<Produto>>() {}.type
        return Gson().fromJson<List<Produto>>(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Produto>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}