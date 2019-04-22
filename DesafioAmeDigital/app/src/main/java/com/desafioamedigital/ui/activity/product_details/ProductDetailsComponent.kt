package com.desafioamedigital.ui.activity.product_details

import dagger.Component

@Component(modules = [ProductDetailsModule::class])
interface ProductDetailsComponent {
    fun inject(activity: ProductDetailsActivity)
}