package br.com.amedigital.challenge_android.di

import br.com.amedigital.challenge_android.view.ui.about_app.AboutAppActivity
import br.com.amedigital.challenge_android.view.ui.categoria.CategoriaActivity
import br.com.amedigital.challenge_android.view.ui.home.HomeActivity
import br.com.amedigital.challenge_android.view.ui.product_detail.ProductDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [HomeActivityFragmentModule::class])
    internal abstract fun contributeMainActivity(): HomeActivity

    @ContributesAndroidInjector
    internal abstract fun contributeAboutAppActivity(): AboutAppActivity

    @ContributesAndroidInjector
    internal abstract fun contributeCategoriaActivity(): CategoriaActivity

    @ContributesAndroidInjector
    internal abstract fun contributeProductDetailActivity(): ProductDetailActivity
}