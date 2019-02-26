package br.com.andrecouto.alodjinha.ui.fragment.category

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CategoryFragmentProvider {

    @ContributesAndroidInjector
    abstract fun provideCategoryFragment(): CategoryFragment
}