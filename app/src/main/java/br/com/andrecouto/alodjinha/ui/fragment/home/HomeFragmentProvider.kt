package br.com.andrecouto.alodjinha.ui.fragment.home

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentProvider {

    @ContributesAndroidInjector
    abstract fun provideHomeFragment(): HomeFragment
}