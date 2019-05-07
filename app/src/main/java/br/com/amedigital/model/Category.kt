package br.com.amedigital.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Category : Serializable{
    @SerializedName("id") var id: Int = 0
    @SerializedName("urlImagem") var urlImage: String = ""
    @SerializedName("descricao") var description: String = ""
}