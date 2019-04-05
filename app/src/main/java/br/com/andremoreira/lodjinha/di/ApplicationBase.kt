package br.com.andremoreira.lodjinha.di

import android.app.Application

import android.support.v7.app.AppCompatActivity

class ApplicationBase : Application() {

    companion object {
        lateinit var appComponent: AppComponent
        lateinit var activityContext : AppCompatActivity
        val isMockAmbiente: Boolean = false
    }

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }

    private fun initializeDagger() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .remoteModule(RemoteModule())
            .build()

    }

}