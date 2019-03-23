package br.com.rbueno.lodjinha.di

import br.com.rbueno.lodjinha.ui.home.HomeActivity
import br.com.rbueno.lodjinha.ui.home.HomeFragment
import br.com.rbueno.lodjinha.ui.product.detail.ProductDetailActivity
import br.com.rbueno.lodjinha.ui.product.list.ProductListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): HomeActivity

    @ContributesAndroidInjector
    abstract fun bindProductDetailActivity(): ProductDetailActivity

    @ContributesAndroidInjector
    abstract fun bindProductListFragment(): ProductListFragment

    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): HomeFragment

}