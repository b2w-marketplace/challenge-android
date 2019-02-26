package marcus.com.br.b2wtest.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import marcus.com.br.b2wtest.BuildConfig
import marcus.com.br.b2wtest.model.api.Api
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    @Named(value = "HTTP_URL")
    fun provideGoogleHttpUrl() = BuildConfig.API

    @Provides
    @Singleton
    fun provideOkHttpClient(builder: OkHttpClient.Builder): OkHttpClient = builder.build()

    @Provides
    @IntoSet
    fun provideInterceptor() = Interceptor { chain ->
        val request = chain.request()
        chain.proceed(request)
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    @Singleton
    fun provideOkHttpBuilder(interceptors: Set<@JvmSuppressWildcards Interceptor>): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
        interceptors.forEach { builder.addInterceptor(it) }
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
        return builder
    }

    @Provides
    @Singleton
    fun provideTwitterApi(client: OkHttpClient, moshi: Moshi, @Named("HTTP_URL") url: String): Api {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .build()
            .create(Api::class.java)
    }
}