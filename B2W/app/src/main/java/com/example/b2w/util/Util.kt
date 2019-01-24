package com.example.b2w.util

import android.content.Context
import android.widget.Toast
import com.example.b2w.model.Banner
import com.example.b2w.model.Category
import com.example.b2w.model.Product
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun mountBanners(param: String): MutableList<Banner>{
    val banners: MutableList<Banner> = mutableListOf()
    val jsonObject = JSONObject(param)
    val jsonArray = JSONArray(jsonObject.get("data").toString())
    for (i in 0 until jsonArray.length()){
        val banner = Gson().fromJson(jsonArray[i].toString(), Banner::class.java)
        banners.add(banner)
    }
    return banners
}

fun mountCategories(param: String): MutableList<Category>{
    val categories: MutableList<Category> = mutableListOf()
    val jsonObject = JSONObject(param)
    val jsonArray = JSONArray(jsonObject.get("data").toString())
    for(i in 0 until jsonArray.length()){
        val category = Gson().fromJson(jsonArray[i].toString(), Category::class.java)
        categories.add(category)
    }
    return categories
}

fun mountProducts(param: String): MutableList<Product>{
    val products: MutableList<Product> = mutableListOf()
    val jsonObject = JSONObject(param)
    val jsonArray = JSONArray(jsonObject.get("data").toString())
    for(i in 0 until jsonArray.length()){
        val product = Gson().fromJson(jsonArray[i].toString(), Product::class.java)
        products.add(product)
    }
    return products
}
