package br.com.b2w.desafio.model.produto

import android.os.Parcelable
import br.com.b2w.desafio.model.Categoria
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Produto (
    @SerializedName("id") val id: Int? = null,
    @SerializedName("nome") val nome: String? = null,
    @SerializedName("urlImagem") val urlImagem: String? = null,
    @SerializedName("descricao") val descricao: String? = null,
    @SerializedName("precoDe") val precoDe: Double? = null,
    @SerializedName("precoPor") val precoPor: Double? = null,
    @SerializedName("categoria")val categoria: Categoria? = null
) : Parcelable