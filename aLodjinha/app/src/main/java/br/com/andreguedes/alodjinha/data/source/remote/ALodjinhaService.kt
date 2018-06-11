package br.com.andreguedes.alodjinha.data.source.remote

import br.com.andreguedes.alodjinha.data.source.RetrofitClient

class ALodjinhaService {

    fun getService(): ALodjinhaAPI {
        return RetrofitClient.getClient().create(ALodjinhaAPI::class.java)
    }

}