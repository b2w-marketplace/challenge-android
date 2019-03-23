package br.com.rbueno.lodjinha.di

import br.com.rbueno.lodjinha.ui.home.HomeActivity
import br.com.rbueno.lodjinha.ui.product.detail.ProductDetailActivity
import br.com.rbueno.lodjinha.ui.product.list.ProductListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): HomeActivity

    @ContributesAndroidInjector
    abstract fun bindProductDetailActivity(): ProductDetailActivity

    @ContributesAndroidInjector
    abstract fun bindProductListActivity(): ProductListActivity

}