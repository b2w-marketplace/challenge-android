package br.com.prodigosorc.lodjinha.models

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonIgnoreProperties(ignoreUnknown = true)
data class Banner(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("urlImagem")
    val urlImagem: String,
    @JsonProperty("linkUrl")
    val linkUrl: String
) : Parcelable
