package com.b2w.lodjinha

import android.app.Application
import timber.log.Timber

open class LodjinhaApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
