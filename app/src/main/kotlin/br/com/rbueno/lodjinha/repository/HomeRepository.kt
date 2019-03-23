package br.com.rbueno.lodjinha.repository

import br.com.rbueno.lodjinha.model.Banner
import br.com.rbueno.lodjinha.network.LodjinhaApi
import io.reactivex.Single

interface HomeRepository {
    fun getBanner(): Single<Banner>
}

class NetworkHomeRepository(private val api: LodjinhaApi) : HomeRepository {
    override fun getBanner() = api.getBanner()
}