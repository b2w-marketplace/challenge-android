package br.com.android.seiji.mobileui.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import br.com.android.seiji.mobileui.di.ViewModelFactory
import br.com.android.seiji.mobileui.di.qualifiers.ViewModelKey
import br.com.android.seiji.presentation.viewModel.*
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
    @IntoMap
    @ViewModelKey(GetBestSellerProductsViewModel::class)
    abstract fun bindGetBestSellerProductsViewModel(viewModel: GetBestSellerProductsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GetCategoriesViewModel::class)
    abstract fun bindGetCategoriesViewModel(viewModel: GetCategoriesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostProductReservationViewModel::class)
    abstract fun bindPostProductReservationViewModel(viewModel: PostProductReservationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GetProductsByCategoryIdViewModel::class)
    abstract fun bindGetProductsByCategoryIdViewModel(viewModel: GetProductsByCategoryIdViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}