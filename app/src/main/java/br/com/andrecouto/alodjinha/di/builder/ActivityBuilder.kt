package br.com.andrecouto.alodjinha.di.builder

import br.com.andrecouto.alodjinha.ui.activity.category.CategoryActivity
import br.com.andrecouto.alodjinha.ui.activity.home.HomeActivity
import br.com.andrecouto.alodjinha.ui.activity.product.ProductDetailsActivity
import br.com.andrecouto.alodjinha.ui.fragment.category.CategoryFragmentProvider
import br.com.andrecouto.alodjinha.ui.fragment.home.HomeFragmentProvider
import br.com.andrecouto.alodjinha.ui.fragment.product.ProductDetailsFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(HomeFragmentProvider::class)])
    internal abstract fun bindHomeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [(CategoryFragmentProvider::class)])
    internal abstract fun bindCategoryActivity(): CategoryActivity

    @ContributesAndroidInjector(modules = [(ProductDetailsFragmentProvider::class)])
    internal abstract fun bindProductDetailsActivity(): ProductDetailsActivity
}