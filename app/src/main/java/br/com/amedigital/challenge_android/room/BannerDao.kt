package br.com.amedigital.challenge_android.room

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.amedigital.challenge_android.models.entity.Banner

@Dao
interface BannerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBannerList(banners: List<Banner>)

    @Query("SELECT * FROM Banner")
    fun getBannerList(): LiveData<List<Banner>>
}