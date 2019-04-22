package com.desafioamedigital.service.di

import com.desafioamedigital.ui.activity.category.CategoryPresenter
import com.desafioamedigital.ui.activity.home.HomePresenter
import com.desafioamedigital.ui.activity.product_details.ProductDetailsPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ConnectionModule::class])
interface ConnectionComponent {
    fun inject(homePresenter: HomePresenter)
    fun inject(productDetailsPresenter: ProductDetailsPresenter)
    fun inject(categoryPresenter: CategoryPresenter)
}