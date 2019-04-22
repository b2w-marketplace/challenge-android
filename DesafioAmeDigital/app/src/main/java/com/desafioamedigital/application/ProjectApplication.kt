package com.desafioamedigital.application

import android.app.Application

class ProjectApplication: Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        initDagger2()
    }

    private fun initDagger2() {
        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
        component.inject(this)
    }
}