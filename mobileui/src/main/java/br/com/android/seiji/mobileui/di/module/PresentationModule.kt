package br.com.android.seiji.mobileui.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import br.com.android.seiji.mobileui.di.ViewModelFactory
import br.com.android.seiji.mobileui.di.qualifiers.ViewModelKey
import br.com.android.seiji.presentation.GetBannersViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(GetBannersViewModel::class)
    abstract fun bindGetBannersViewModel(viewModel: GetBannersViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}