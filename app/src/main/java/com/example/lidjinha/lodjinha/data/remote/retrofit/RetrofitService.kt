package com.example.lidjinha.lodjinha.data.remote.retrofit

import com.example.lidjinha.lodjinha.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    companion object {

        private val mInstance = Retrofit.Builder()
                .baseUrl(BuildConfig.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        private fun instance(): Retrofit {
            return mInstance
        }

        fun getBannerService(): BannerService {
            return instance().create(BannerService::class.java)
        }

        fun getCategorieService(): CategoriesService {
            return instance().create(CategoriesService::class.java)
        }

        fun getBestSellers(): ProductService {
            return instance().create(ProductService::class.java)
        }

        fun getReserve(): ProductService {
            return instance().create(ProductService::class.java)
        }
    }
}