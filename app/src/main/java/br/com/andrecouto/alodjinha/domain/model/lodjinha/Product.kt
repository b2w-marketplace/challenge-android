package br.com.andrecouto.alodjinha.domain.model.lodjinha

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import android.arch.persistence.room.Ignore

@Entity(tableName = "products")
data class Product (
        @SerializedName("id") @PrimaryKey var id: Int = 0,
        @SerializedName("descricao") var description: String? = "",
        @SerializedName("nome") var name: String? = "",
        @SerializedName("precoDe") var priceFrom: Float = 0.0f,
        @SerializedName("precoPor") var priceFor: Float = 0.0f,
        @SerializedName("urlImagem") var urlImage: String? = "",
        @Ignore @SerializedName("categoria") var category: Category? = null) : Serializable