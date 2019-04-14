package br.com.dafle.alodjinha

import android.app.Application
import br.com.dafle.alodjinha.koin.ModulesTest
import org.koin.android.ext.android.startKoin

class LodjinhaApplicationTest: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(
                ModulesTest.appModule,
                ModulesTest.businnesModule,
                ModulesTest.viewModelModule
            )
        )
    }
}