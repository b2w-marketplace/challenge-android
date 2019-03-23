package br.com.rbueno.lodjinha.di

import br.com.rbueno.lodjinha.ui.home.HomeActivity
import br.com.rbueno.lodjinha.ui.home.banner.BannerFragment
import br.com.rbueno.lodjinha.ui.home.category.CategoryFragment
import br.com.rbueno.lodjinha.ui.home.mostsold.MostSoldFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): HomeActivity

    @ContributesAndroidInjector
    abstract fun bindBannerFragment(): BannerFragment

    @ContributesAndroidInjector
    abstract fun bindCategoryFragment(): CategoryFragment

    @ContributesAndroidInjector
    abstract fun bindMostSoldFragment(): MostSoldFragment


}