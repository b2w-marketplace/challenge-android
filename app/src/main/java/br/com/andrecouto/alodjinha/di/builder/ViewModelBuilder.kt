package br.com.andrecouto.alodjinha.di.builder

import android.arch.lifecycle.ViewModelProvider
import br.com.andrecouto.alodjinha.ui.fragment.category.CategoryViewModelBuilder
import br.com.andrecouto.alodjinha.ui.fragment.home.HomeViewModelBuilder
import br.com.andrecouto.alodjinha.ui.fragment.product.ProductDetailsViewModelBuilder
import br.com.andrecouto.alodjinha.viewmodel.LodjinhaViewModelFactory
import dagger.Binds
import dagger.Module

@Module(includes = [
    HomeViewModelBuilder::class,
    CategoryViewModelBuilder::class,
    ProductDetailsViewModelBuilder::class
])
abstract class ViewModelBuilder {

    @Binds
    abstract fun bindViewModelFactory(lodjinhaModelFactory: LodjinhaViewModelFactory): ViewModelProvider.Factory
}