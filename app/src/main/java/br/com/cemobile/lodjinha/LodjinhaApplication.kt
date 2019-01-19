package br.com.cemobile.lodjinha

import android.app.Application
import br.com.cemobile.lodjinha.di.*
import com.facebook.stetho.Stetho
import org.koin.android.ext.android.startKoin

class LodjinhaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupStetho()
        setupKoin()
    }

    private fun setupStetho() {
        Stetho.initializeWithDefaults(this)
    }

    private fun setupKoin() {
        startKoin(this,
            listOf(
                appModule,
                dataModule,
                domainModule,
                databaseModule,
                imageModule
            )
        )
    }

}