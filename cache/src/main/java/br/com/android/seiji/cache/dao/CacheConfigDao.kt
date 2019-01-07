package br.com.android.seiji.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import br.com.android.seiji.cache.db.CacheConfigConstants
import br.com.android.seiji.cache.model.CacheConfig
import io.reactivex.Maybe

@Dao
abstract class CacheConfigDao {

    @Query(CacheConfigConstants.QUERY_CACHE_CONFIG)
    abstract fun getCacheConfig(): Maybe<CacheConfig>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCacheConfig(cacheConfig: CacheConfig)
}