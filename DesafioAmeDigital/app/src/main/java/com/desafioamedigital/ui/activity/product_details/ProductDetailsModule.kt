package com.desafioamedigital.ui.activity.product_details

import dagger.Module
import dagger.Provides

@Module
class ProductDetailsModule(private val activity: ProductDetailsActivity) {

    @Provides
    fun providesActivity(): ProductDetailsActivity = activity

    @Provides
    fun providesPresenter(): ProductDetailsPresenter = ProductDetailsPresenter()


}