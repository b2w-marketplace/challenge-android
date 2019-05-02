package br.com.amedigital.challenge_android.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.amedigital.challenge_android.models.entity.Banner
import br.com.amedigital.challenge_android.models.entity.Categoria
import br.com.amedigital.challenge_android.models.entity.Produto
import br.com.amedigital.challenge_android.utils.*

@Database(entities = [(Banner::class), (Categoria::class), (Produto::class)], version = 1, exportSchema = false)
@TypeConverters(value = [(StringListConverter::class),
    (IntegerListConverter::class),
    (BannerListConverter::class),
    //(CategoriaConverter::class),
    (CategoriaListConverter::class),
    (ProdutoListConverter::class)])
abstract class AppDatabase : RoomDatabase() {
    abstract fun bannerDao(): BannerDao
    abstract fun categoriaDao(): CategoriaDao
    abstract fun produtoDao(): ProdutoDao
}