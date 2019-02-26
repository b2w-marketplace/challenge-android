package br.com.andrecouto.alodjinha.data.source.local.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import br.com.andrecouto.alodjinha.data.source.local.db.AppDataBase.Companion.DATABASE_LODJINHA_VERSION
import br.com.andrecouto.alodjinha.data.source.local.db.banner.BannerDao
import br.com.andrecouto.alodjinha.data.source.local.db.category.CategoryDao
import br.com.andrecouto.alodjinha.data.source.local.db.product.ProductDao
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Banner
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Category
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Product

@Database(entities = arrayOf(Banner::class, Category::class, Product::class), version = DATABASE_LODJINHA_VERSION,exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    companion object  {
        const val DATABASE_LODJINHA = "lodjinha.db"
        const val DATABASE_LODJINHA_VERSION = 1
        const val TABLE_BANNER = "banner"
    }

    abstract fun getProductDao(): ProductDao
    abstract fun getCategoryDao(): CategoryDao
    abstract fun getBannerDao(): BannerDao

}