package br.com.andrecouto.alodjinha.data.source.local.db.banner

import android.arch.persistence.room.*
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Banner

@Dao
interface BannerDao {

    @Query("SELECT * FROM banners")
    fun getBanners(): List<Banner>

    @Query("SELECT * FROM banners WHERE id=:bannerId")
    fun get(bannerId: Int): Banner?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveBanner(movie: Banner)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllBanners(movies: List<Banner>)

    @Delete
    fun removeBanner(movie: Banner)

    @Query("DELETE FROM banners")
    fun clear()
}