package br.com.prodigosorc.lodjinha.models

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonIgnoreProperties(ignoreUnknown = true)
data class Produto(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("nome")
    val nome: String,
    @JsonProperty("urlImagem")
    val urlImagem: String,
    @JsonProperty("descricao")
    val descricao: String,
    @JsonProperty("precoDe")
    val precoDe: Double,
    @JsonProperty("precoPor")
    val precoPor: Double,
    @JsonProperty("categoria")
    val categoria: Categoria
) : Parcelable