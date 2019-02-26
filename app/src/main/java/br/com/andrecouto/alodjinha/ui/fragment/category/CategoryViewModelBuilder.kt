package br.com.andrecouto.alodjinha.ui.fragment.category

import android.arch.lifecycle.ViewModel
import br.com.andrecouto.alodjinha.di.qualifier.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CategoryViewModelBuilder {
    @Binds
    @IntoMap
    @ViewModelKey(CategoryViewModel::class)
    abstract fun bindCategoryViewModel(categoryViewModel: CategoryViewModel): ViewModel
}