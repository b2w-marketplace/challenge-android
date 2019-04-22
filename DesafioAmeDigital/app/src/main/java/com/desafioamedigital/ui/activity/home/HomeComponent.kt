package com.desafioamedigital.ui.activity.home

import dagger.Component

@Component(modules = [HomeModule::class])
interface HomeComponent {
    fun inject(activity: HomeActivity)
}