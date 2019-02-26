package br.com.andrecouto.alodjinha.ui.fragment.home

import android.arch.lifecycle.ViewModel
import br.com.andrecouto.alodjinha.di.qualifier.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeViewModelBuilder {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel
}