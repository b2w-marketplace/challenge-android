package br.com.b2w.lodjinha

import android.app.Application
import br.com.b2w.lodjinha.base.DependencyInjection
import org.koin.android.ext.android.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(DependencyInjection.homeModule))
    }

}