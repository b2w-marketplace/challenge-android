package br.com.amedigital.challenge_android.di

import br.com.amedigital.challenge_android.view.ui.home.BannerListFragment
import br.com.amedigital.challenge_android.view.ui.home.CategoriaListFragment
import br.com.amedigital.challenge_android.view.ui.home.MaisVendidosListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class HomeActivityFragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeBannerListFragment(): BannerListFragment

    @ContributesAndroidInjector
    abstract fun contributeCategoriaListFragment(): CategoriaListFragment

    @ContributesAndroidInjector
    abstract fun contributeMaisVendidosListFragment(): MaisVendidosListFragment
}