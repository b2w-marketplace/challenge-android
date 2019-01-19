package br.com.cemobile.lodjinha.di

import br.com.cemobile.lodjinha.ui.home.CategoryViewModel
import br.com.cemobile.lodjinha.ui.home.HomeViewModel
import br.com.cemobile.lodjinha.ui.product.detail.ProductDetailsViewModel
import br.com.cemobile.lodjinha.ui.product.list.CategoryProductsViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {

    viewModel { HomeViewModel(get()) }

    viewModel { ProductDetailsViewModel(get()) }

    viewModel { CategoryViewModel(get()) }

    viewModel { (categoryId: Long) -> CategoryProductsViewModel(categoryId, get()) }

}