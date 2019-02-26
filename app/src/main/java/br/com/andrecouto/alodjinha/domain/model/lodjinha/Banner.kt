package br.com.andrecouto.alodjinha.domain.model.lodjinha

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "banners")
data class Banner(@SerializedName("id") @PrimaryKey var id : Int,
                  @SerializedName("urlImagem") var urlImage : String?,
                  @SerializedName("linkUrl") var urlLink : String?) : Serializable
