package br.com.android.seiji.mobileui.di.module

import android.app.Application
import br.com.android.seiji.cache.BannersCacheImpl
import br.com.android.seiji.cache.db.CacheDatabase
import br.com.android.seiji.data.repository.BannersCache
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class CacheModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesDataBase(application: Application): CacheDatabase {
            return CacheDatabase.getInstances(application)
        }
    }

    @Binds
    abstract fun bindBannersCache(bannersCache: BannersCacheImpl): BannersCache
}