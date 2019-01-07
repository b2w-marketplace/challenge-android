package br.com.android.seiji.cache.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import br.com.android.seiji.cache.dao.CacheConfigDao
import br.com.android.seiji.cache.dao.CachedBannerDao
import br.com.android.seiji.cache.model.CacheConfig
import br.com.android.seiji.cache.model.CachedBanner
import javax.inject.Inject

@Database(entities = [CacheConfig::class, CachedBanner::class], version = 1)
abstract class CacheDatabase @Inject constructor() : RoomDatabase() {

    abstract fun cachedBannerDao(): CachedBannerDao
    abstract fun cacheConfigDao(): CacheConfigDao

    companion object {

        private var INSTANCE: CacheDatabase? = null
        private val lock = Any()
        private const val DATABASE_NAME = "cachedatabase.db"

        fun getInstances(context: Context): CacheDatabase {
            if (INSTANCE == null) {
                synchronized(lock) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            CacheDatabase::class.java,
                            DATABASE_NAME
                        ).build()
                    }
                    return INSTANCE as CacheDatabase
                }
            }
            return INSTANCE as CacheDatabase
        }
    }
}