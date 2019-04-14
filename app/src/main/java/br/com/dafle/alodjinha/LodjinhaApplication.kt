package br.com.dafle.alodjinha

import android.app.Application
import br.com.dafle.alodjinha.koin.Modules
import org.koin.android.ext.android.startKoin

open class LodjinhaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(
                Modules.appModule,
                Modules.businnesModule,
                Modules.viewModelModule
            )
        )
    }
}
