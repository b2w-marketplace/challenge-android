package br.com.andrecouto.alodjinha.ui.fragment.product

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ProductDetailsFragmentProvider {

    @ContributesAndroidInjector
    abstract fun provideCategoryFragment(): ProductDetailsFragment
}