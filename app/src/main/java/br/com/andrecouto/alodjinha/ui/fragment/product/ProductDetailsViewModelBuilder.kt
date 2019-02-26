package br.com.andrecouto.alodjinha.ui.fragment.product

import android.arch.lifecycle.ViewModel
import br.com.andrecouto.alodjinha.di.qualifier.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ProductDetailsViewModelBuilder {
    @Binds
    @IntoMap
    @ViewModelKey(ProductDetailsViewModel::class)
    abstract fun bindProductDetailsViewModel(productDetailsViewModel: ProductDetailsViewModel): ViewModel
}