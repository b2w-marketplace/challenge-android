package com.desafioamedigital.ui.activity.category

import dagger.Module
import dagger.Provides

@Module
class CategoryModule(private val activity: CategoryActivity) {

    @Provides
    fun provideActivity(): CategoryActivity = activity

    @Provides
    fun providePresenter(): CategoryContract.Presenter = CategoryPresenter()

}