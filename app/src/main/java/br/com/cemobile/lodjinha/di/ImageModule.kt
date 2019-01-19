package br.com.cemobile.lodjinha.di

import br.com.cemobile.lodjinha.util.image.GlideImageDownloader
import br.com.cemobile.lodjinha.util.image.ImageDownloader
import org.koin.dsl.module.module

val imageModule = module {

    single<ImageDownloader> { GlideImageDownloader(get()) }

}