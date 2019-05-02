package br.com.amedigital.challenge_android.utils

import androidx.room.TypeConverter
import br.com.amedigital.challenge_android.models.entity.Banner
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

open class BannerListConverter {
    @TypeConverter
    fun fromString(value: String): List<Banner>? {
        val listType = object : TypeToken<List<Banner>>() {}.type
        return Gson().fromJson<List<Banner>>(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Banner>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}