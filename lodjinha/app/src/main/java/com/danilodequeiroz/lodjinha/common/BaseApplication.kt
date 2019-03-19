package com.danilodequeiroz.lodjinha.common

import android.app.Application
import com.danilodequeiroz.lodjinha.di.appComponents
import org.koin.android.ext.android.startKoin

class BaseApplication : Application() {

    companion object { var mock = false }

    override fun onCreate() {
        super.onCreate()
        startKoin(this, appComponents)
    }
}