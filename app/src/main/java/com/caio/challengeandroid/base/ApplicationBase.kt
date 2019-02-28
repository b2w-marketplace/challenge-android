package com.caio.lodjinha.base

import android.app.Application
import com.caio.lodjinha.di.DependencyInjection
import org.koin.android.ext.android.startKoin

class ApplicationBase : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(
            DependencyInjection.networkModule,
            DependencyInjection.homeModule,
            DependencyInjection.productDetailModule,
            DependencyInjection.productListModule,
            DependencyInjection.repositoryModule
        ))
    }

}