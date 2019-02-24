package br.com.prodigosorc.lodjinha.models

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonIgnoreProperties(ignoreUnknown = true)
data class Categoria(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("descricao")
    val descricao: String,
    @JsonProperty("urlImagem")
    val urlImagem: String
) : Parcelable