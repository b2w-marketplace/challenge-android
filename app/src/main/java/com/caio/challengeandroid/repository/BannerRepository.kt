package com.caio.lodjinha.repository

import com.caio.lodjinha.repository.remote.BannerREST
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BannerRepository (private val bannerREST: BannerREST) {

    suspend fun getBanner() = withContext(Dispatchers.IO){
        return@withContext bannerREST.getBanner().await().data
    }
}