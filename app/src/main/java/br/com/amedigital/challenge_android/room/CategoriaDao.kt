package br.com.amedigital.challenge_android.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.amedigital.challenge_android.models.entity.Categoria

@Dao
interface CategoriaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategoriaList(banners: List<Categoria>)

    @Query("SELECT * FROM Categoria")
    fun getCategoriaList(): LiveData<List<Categoria>>
}