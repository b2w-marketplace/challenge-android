package br.com.cemobile.data.local.database

import android.arch.persistence.room.*

@Dao
interface LodjinhaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) fun saveBanners(entities: List<BannerEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE) fun saveCategories(entities: List<CategoryEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE) fun saveProducts(entities: List<ProductEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE) fun saveProduct(entity: ProductEntity)

    @Update
    fun updateProducts(entities: List<ProductEntity>)

    @Query(value = ALL_BANNERS) fun banners(): List<BannerEntity>

    @Query(value = ALL_CATEGORIES) fun categories(): List<CategoryEntity>

    @Query(value = ALL_PRODUCTS) fun products(): List<ProductWithCategoryEntity>

    @Query(value = BEST_SELLING_PRODUCTS) fun bestSellingProducts(): List<ProductWithCategoryEntity>

    @Query(value = PRODUCT_BY_ID) fun product(id: Long): ProductWithCategoryEntity

    @Query(value = DELETE_ALL_BANNERS) fun deleteAllBanners()

    @Query(value = DELETE_ALL_CATEGORIES) fun deleteAllCategories()

    @Query(value = DELETE_ALL_PRODUCTS) fun deleteAllProducts()

    private companion object {
        const val ALL_BANNERS = "SELECT * FROM banners"

        const val DELETE_ALL_BANNERS = "DELETE FROM banners"

        const val ALL_CATEGORIES = "SELECT * FROM categorias"

        const val DELETE_ALL_CATEGORIES = "DELETE FROM categorias"

        const val ALL_PRODUCTS =
            "SELECT produtos.*, categorias.descricao as descricao_categoria, categorias.url_imagem " +
            "as url_imagem_categoria FROM produtos INNER JOIN categorias " +
            "ON produtos.categoria_id = categorias.id"

        const val BEST_SELLING_PRODUCTS =
            "SELECT produtos.*, categorias.descricao as descricao_categoria, categorias.url_imagem " +
            "as url_imagem_categoria FROM produtos INNER JOIN categorias " +
            "ON produtos.categoria_id = categorias.id WHERE produtos.mais_vendido = 0"

        const val DELETE_ALL_PRODUCTS = "DELETE FROM produtos"

        const val PRODUCT_BY_ID =
            "SELECT produtos.*, categorias.descricao as descricao_categoria, categorias.url_imagem " +
            "as url_imagem_categoria FROM produtos INNER JOIN categorias " +
            "ON produtos.categoria_id = categorias.id WHERE produtos.id = :id"
    }

}