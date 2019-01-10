package br.com.android.seiji.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import br.com.android.seiji.cache.db.CategoriesConstants
import br.com.android.seiji.cache.model.CachedCategory
import io.reactivex.Flowable

@Dao
abstract class CachedCategoryDao {

    @Query(CategoriesConstants.QUERY_CATEGORIES)
    abstract fun getCategories(): Flowable<List<CachedCategory>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCategories(categories: List<CachedCategory>)

    @Query(CategoriesConstants.DELETE_CATEGORIES)
    abstract fun deleteCategories()
}