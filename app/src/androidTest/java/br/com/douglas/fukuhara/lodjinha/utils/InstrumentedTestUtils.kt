package br.com.douglas.fukuhara.lodjinha.utils

import android.content.Context
import com.google.gson.Gson

object InstrumentedTestUtils {

    fun <T> getObjFromJson(instrumentedTestContext: Context, json: String, classType: Class<T>): T {
        val stringFromJson = getStringFromJson(instrumentedTestContext, json)
        return Gson().fromJson(stringFromJson, classType)
    }

    fun getStringFromJson(instrumentedTestContext: Context, json: String): String {
        val inputStream = instrumentedTestContext.resources.assets.open(json)
        return inputStream.bufferedReader().use { it.readText() }
    }

    fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
        return RecyclerViewMatcher(recyclerViewId)
    }

}