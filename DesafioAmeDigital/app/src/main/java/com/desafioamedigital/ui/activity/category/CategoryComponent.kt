package com.desafioamedigital.ui.activity.category

import dagger.Component

@Component(modules = [CategoryModule::class])
interface CategoryComponent {
    fun inject(activity: CategoryActivity)
}