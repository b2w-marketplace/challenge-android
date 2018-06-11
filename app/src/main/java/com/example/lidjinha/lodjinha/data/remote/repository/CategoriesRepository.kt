package com.example.lidjinha.lodjinha.data.remote.repository

import android.util.Log
import com.example.lidjinha.lodjinha.data.model.WSResponse
import com.example.lidjinha.lodjinha.data.remote.retrofit.RetrofitService
import com.example.lidjinha.lodjinha.model.Categorie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.reflect.KFunction1

class CategoriesRepository {

    companion object {
        val instance: CategoriesRepository by lazy { CategoriesRepository() }
    }

    private lateinit var callCategories: Call<WSResponse<List<Categorie>>>


    fun getCategories(onCategoriesRetrieved: KFunction1<List<Categorie>, Unit>) {

        callCategories = RetrofitService.getCategorieService().categories()
        callCategories.enqueue(categoriesCallback(onCategoriesRetrieved))
    }

    private fun categoriesCallback(onCategoriesRetrieved: KFunction1<List<Categorie>, Unit>) = object : Callback<WSResponse<List<Categorie>>> {

        override fun onResponse(call: Call<WSResponse<List<Categorie>>>?, response: Response<WSResponse<List<Categorie>>>?) {
            response.apply { this?.body()?.let { it.body?.let { it1 -> onCategoriesRetrieved(it1) } } }

        }

        override fun onFailure(call: Call<WSResponse<List<Categorie>>>?, t: Throwable) {
            Log.d("Categoires Failure:", t.message)
        }
    }
}