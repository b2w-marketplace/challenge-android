package com.desafioamedigital.ui.activity.home

import dagger.Module
import dagger.Provides

@Module
class HomeModule(private val activity: HomeActivity) {

    @Provides
    fun provideActivity(): HomeActivity = activity

    @Provides
    fun providePresenter(): HomeContract.Presenter = HomePresenter()

}