package br.com.prodigosorc.lodjinha.retrofit

import br.com.prodigosorc.lodjinha.retrofit.services.BannerService
import br.com.prodigosorc.lodjinha.retrofit.services.CategoriaService
import br.com.prodigosorc.lodjinha.retrofit.services.ProdutoService
import br.com.prodigosorc.lodjinha.util.Constants.Companion.PREFIXO_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class RetrofitInicializador {

    private var retrofit: Retrofit
    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
        client.addInterceptor(interceptor)
        retrofit = Retrofit.Builder()
            .baseUrl(PREFIXO_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .client(client.build())
            .build()
    }

    fun getBannerService(): BannerService {
        return retrofit.create<BannerService>(BannerService::class.java)
    }

    fun getCategoriaService(): CategoriaService {
        return retrofit.create<CategoriaService>(CategoriaService::class.java)
    }

    fun getProdutoService(): ProdutoService {
        return retrofit.create<ProdutoService>(ProdutoService::class.java)
    }

}