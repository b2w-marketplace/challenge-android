package br.com.cemobile.data.local.database

import android.arch.persistence.room.*
import br.com.cemobile.domain.model.Product

@Entity(tableName = "banners")
class BannerEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "url_imagem") val imageUrl: String,
    @ColumnInfo(name = "link_url") val linkUrl: String
)

@Entity(
    tableName = "categorias",
    indices = [(Index(value = ["id"], unique = true))]
)
class CategoryEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "descricao") val description: String,
    @ColumnInfo(name = "url_imagem") val imageUrl: String
)

@Entity(
    tableName = "produtos",
    foreignKeys = [(
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoria_id"]
        )
    )]
)
class ProductEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "nome") val name: String,
    @ColumnInfo(name = "url_imagem") val imageUrl: String,
    @ColumnInfo(name = "descricao") val description: String,
    @ColumnInfo(name = "preco_de") val fromPrice: Float,
    @ColumnInfo(name = "preco_por") val byPrice: Float,
    @ColumnInfo(name = "mais_vendido") val bestSelling: Boolean,
    @ColumnInfo(name = "categoria_id") val categoryId: Long
)

data class ProductWithCategoryEntity(
    @Embedded
    var product: ProductEntity,
    @ColumnInfo(name = "descricao_categoria") var categoryDescription: String,
    @ColumnInfo(name = "url_imagem_categoria") var categoryImageUrl: String
)

data class ProductWithCategory(
    @Embedded val product: Product,
    @ColumnInfo(name = "descricao") val categoryDescription: String,
    @ColumnInfo(name = "url_imagem") val categoryUrlImage: String
)