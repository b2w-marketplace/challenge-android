package br.com.andremoreira.lodjinha.di

import android.content.Context
import base.imonitore.com.br.baseapplication.servicesmanager.MockInterceptor
import br.com.andremoreira.lodjinha.repository.RemoteConstant
import br.com.andremoreira.lodjinha.repository.remote.banner.BannerREST
import br.com.andremoreira.lodjinha.repository.remote.category.CategoryREST
import br.com.andremoreira.lodjinha.repository.remote.product.ProductREST
import br.com.andremoreira.lodjinha.utils.LoadingDialog

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RemoteModule {

    @Provides @Singleton fun provideGson(): Gson =
            GsonBuilder().setLenient().create()

    @Provides @Singleton fun provideMockInterceptor(context: Context): MockInterceptor =
            MockInterceptor(context)

    @Provides @Singleton fun provideOkHttpClient(mockInterceptor: MockInterceptor): OkHttpClient =
            OkHttpClient.Builder()
                    .addInterceptor(if(ApplicationBase.isMockAmbiente) mockInterceptor
                        else HttpLoggingInterceptor()
                            .setLevel(HttpLoggingInterceptor.Level.BODY))
                    .readTimeout(20, TimeUnit.SECONDS)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .build()

    @Provides @Singleton fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .baseUrl(RemoteConstant.URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build()

    @Provides @Singleton fun provideProductREST(retrofit: Retrofit): ProductREST =
            retrofit.create(ProductREST::class.java)

    @Provides @Singleton fun provideBannerREST(retrofit: Retrofit): BannerREST =
        retrofit.create(BannerREST::class.java)

    @Provides @Singleton fun provideCategoryREST(retrofit: Retrofit): CategoryREST =
        retrofit.create(CategoryREST::class.java)


    @Provides @Singleton fun provideLoadingDialog(context: Context): LoadingDialog =
            LoadingDialog(context)
}