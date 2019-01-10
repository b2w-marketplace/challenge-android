package br.com.android.seiji.cache.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import br.com.android.seiji.cache.db.ProductsConstants

@Entity(tableName = ProductsConstants.TABLE_NAME)
data class CachedProduct(

        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = ProductsConstants.COLUMN_PRODUCT_ID)
        var id: Int = -1,

        @ColumnInfo(name = ProductsConstants.COLUMN_NOME)
        var nome: String,

        @ColumnInfo(name = ProductsConstants.COLUMN_DESCRICAO)
        var descricao: String,

        @ColumnInfo(name = ProductsConstants.COLUMN_PRECO_DE)
        var precoDe: Double,

        @ColumnInfo(name = ProductsConstants.COLUMN_PRECO_POR)
        var precoPor: Double,

        @ColumnInfo(name = ProductsConstants.COLUMN_URL_IMAGEM)
        var urlImagem: String,

        @Embedded
        var categoria: CachedCategory
)