package br.com.andrecouto.alodjinha.domain.model.lodjinha

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "products")
data class Product (
        @SerializedName("id") @PrimaryKey var id: Int,
        @SerializedName("descricao") var description: String?,
        @SerializedName("nome") var name: String?,
        @SerializedName("precoDe") var priceFrom: Float,
        @SerializedName("precoPor") var priceFor: Float,
        @SerializedName("urlImagem") var urlImage: String?) : Serializable