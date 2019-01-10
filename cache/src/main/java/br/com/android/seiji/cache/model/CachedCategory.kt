package br.com.android.seiji.cache.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import br.com.android.seiji.cache.db.CategoriesConstants

@Entity(tableName = CategoriesConstants.TABLE_NAME)
data class CachedCategory(

        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = CategoriesConstants.COLUMN_CATEGORY_ID)
        var id: Int = -1,

        @ColumnInfo(name = CategoriesConstants.COLUMN_DESCRICAO)
        var categoriaDescricao: String,

        @ColumnInfo(name = CategoriesConstants.COLUMN_URL_IMAGEM)
        var categoriaUrlImagem: String
)