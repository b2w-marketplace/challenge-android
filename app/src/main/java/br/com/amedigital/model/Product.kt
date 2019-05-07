package br.com.amedigital.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Product : Serializable{
    @SerializedName("id") var id: Int = 0
    @SerializedName("urlImagem") var urlImage: String = ""
    @SerializedName("nome") var name: String = ""
    @SerializedName("descricao") var description: String = ""
    @SerializedName("precoDe") var priceOf: Double = 0.0
    @SerializedName("precoPor") var priceFor: Double = 0.0
    @SerializedName("categoria") var category:Category? = null
}