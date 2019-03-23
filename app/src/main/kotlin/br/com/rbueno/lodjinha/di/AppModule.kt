package br.com.rbueno.lodjinha.di

import br.com.rbueno.lodjinha.BuildConfig
import br.com.rbueno.lodjinha.network.LodjinhaApi
import br.com.rbueno.lodjinha.network.NetworkConfig
import br.com.rbueno.lodjinha.repository.HomeRepository
import br.com.rbueno.lodjinha.repository.NetworkHomeRepository
import br.com.rbueno.lodjinha.repository.NetworkProductRepository
import br.com.rbueno.lodjinha.repository.ProductRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideApi(): LodjinhaApi = NetworkConfig.createApi(BuildConfig.BASE_URL)

    @Singleton
    @Provides
    fun provideHomeRepository(api: LodjinhaApi): HomeRepository = NetworkHomeRepository(api)

    @Singleton
    @Provides
    fun provideProductRepository(api: LodjinhaApi): ProductRepository = NetworkProductRepository(api)
}
