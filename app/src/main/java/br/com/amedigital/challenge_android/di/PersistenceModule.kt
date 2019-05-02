package br.com.amedigital.challenge_android.di

import android.app.Application
import androidx.annotation.NonNull
import androidx.room.Room
import br.com.amedigital.challenge_android.room.AppDatabase
import br.com.amedigital.challenge_android.room.BannerDao
import br.com.amedigital.challenge_android.room.CategoriaDao
import br.com.amedigital.challenge_android.room.ProdutoDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PersistenceModule {

    @Provides
    @Singleton
    fun provideDatabase(@NonNull application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "ALodjinha.db").allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    fun provideBannerDao(@NonNull database: AppDatabase): BannerDao {
        return database.bannerDao()
    }

    @Provides
    @Singleton
    fun provideCategoriaDao(@NonNull database: AppDatabase): CategoriaDao {
        return database.categoriaDao()
    }

    @Provides
    @Singleton
    fun provideProdutoDao(@NonNull database: AppDatabase): ProdutoDao {
        return database.produtoDao()
    }
}