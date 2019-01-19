package br.com.cemobile.lodjinha.di

import android.arch.persistence.room.Room
import br.com.cemobile.data.local.database.LodjinhaDatabase
import br.com.cemobile.lodjinha.util.DB_NAME
import org.koin.dsl.module.module

val databaseModule = module {

    // AppDatabase
    single {
        Room.databaseBuilder(get(), LodjinhaDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
    // AppDao
    single {
        val db: LodjinhaDatabase = get()
        db.dao()
    }

}