package br.com.android.seiji.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import br.com.android.seiji.cache.db.BannersConstants
import br.com.android.seiji.cache.model.CachedBanner
import io.reactivex.Flowable

@Dao
abstract class CachedBannerDao {

    @Query(BannersConstants.QUERY_BANNERS)
    abstract fun getBanners(): Flowable<List<CachedBanner>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertBanners(banners: List<CachedBanner>)

    @Query(BannersConstants.DELETE_BANNERS)
    abstract fun deleteBanners()
}