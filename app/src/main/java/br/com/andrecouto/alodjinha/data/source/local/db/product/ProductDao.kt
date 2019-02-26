package br.com.andrecouto.alodjinha.data.source.local.db.product

import android.arch.persistence.room.*
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM products")
    fun getProducts(): List<Product>

    @Query("SELECT * FROM products WHERE id=:productId")
    fun get(productId: Int): Product?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveProduct(category: Product)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllProducts(products: List<Product>)

    @Delete
    fun removeProduct(product: Product)

    @Query("DELETE FROM products")
    fun clear()
}