package br.com.cemobile.domain.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded

data class ProductWithCategory(
    @Embedded val product: Product,
    @ColumnInfo(name = "descricao") val categoryDescription: String,
    @ColumnInfo(name = "url_imagem") val categoryUrlImage: String
)