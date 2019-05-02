package br.com.amedigital.challenge_android.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.amedigital.challenge_android.models.entity.Categoria
import br.com.amedigital.challenge_android.models.entity.Produto

@Dao
interface ProdutoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProdutoList(banners: List<Produto>)

    @Query("SELECT * FROM PRODUTO WHERE id = :id_")
    fun getProduto(id_: Int): Produto

    @Query("SELECT * FROM Produto")
    fun getProdutoList(): LiveData<List<Produto>>
}