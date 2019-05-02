package br.com.amedigital.challenge_android.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.amedigital.challenge_android.factory.AppViewModelFactory
import br.com.amedigital.challenge_android.view.ui.categoria.CategoriaActivityViewModel
import br.com.amedigital.challenge_android.view.ui.home.HomeActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeActivityViewModel::class)
    internal abstract fun bindMainActivityViewModels(homeActivityViewModel: HomeActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CategoriaActivityViewModel::class)
    internal abstract fun bindCategoriaActivityViewModels(categoriaActivityViewModel: CategoriaActivityViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}