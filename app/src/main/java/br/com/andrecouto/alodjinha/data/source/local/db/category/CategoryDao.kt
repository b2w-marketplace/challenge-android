package br.com.andrecouto.alodjinha.data.source.local.db.category

import android.arch.persistence.room.*
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Category

@Dao
interface CategoryDao {

    @Query("SELECT * FROM categories")
    fun getCategories(): List<Category>

    @Query("SELECT * FROM categories WHERE id=:categoryId")
    fun get(categoryId: Int): Category

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCategory(category: Category)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllCategories(categories: List<Category>)

    @Delete
    fun removeCategory(category: Category)

    @Query("DELETE FROM categories")
    fun clear()
}