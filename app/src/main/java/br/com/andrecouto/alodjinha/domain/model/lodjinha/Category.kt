package br.com.andrecouto.alodjinha.domain.model.lodjinha

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "categories")
data class Category(@SerializedName("id") @PrimaryKey var id : Int,
                    @SerializedName("descricao") var description : String?,
                    @SerializedName("urlImagem") var urlImage : String?) : Serializable