package br.com.cemobile.data.local.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(
    version = 1,
    entities = [BannerEntity::class, CategoryEntity::class, ProductEntity::class],
    exportSchema = false
)
abstract class LodjinhaDatabase : RoomDatabase() {
    abstract fun dao(): LodjinhaDao
}