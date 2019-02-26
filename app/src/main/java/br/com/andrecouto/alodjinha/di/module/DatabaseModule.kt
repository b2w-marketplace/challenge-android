package br.com.andrecouto.alodjinha.di.module

import android.app.Application
import android.arch.persistence.room.Room
import br.com.andrecouto.alodjinha.data.source.local.db.AppDataBase
import br.com.andrecouto.alodjinha.data.source.local.db.banner.BannerDao
import br.com.andrecouto.alodjinha.data.source.local.db.category.CategoryDao
import br.com.andrecouto.alodjinha.data.source.local.db.product.ProductDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(application: Application): AppDataBase {
        return Room
                .databaseBuilder(application, AppDataBase::class.java, AppDataBase.DATABASE_LODJINHA)
                .fallbackToDestructiveMigration()
                .build()
    }

    @Provides
    fun provideBannerDao(appDataBase: AppDataBase): BannerDao {
        return appDataBase.getBannerDao()
    }

    @Provides
    fun provideCategoryDao(appDataBase: AppDataBase): CategoryDao {
        return appDataBase.getCategoryDao()
    }

    @Provides
    fun provideProductDao(appDataBase: AppDataBase): ProductDao {
        return appDataBase.getProductDao()
    }
}