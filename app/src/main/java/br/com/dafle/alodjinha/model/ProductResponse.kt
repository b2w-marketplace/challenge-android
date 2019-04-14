package br.com.dafle.alodjinha.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductResponse(val data: List<Product>, val offset: Int?, val total: Int?): Parcelable

@Parcelize
data class Product(val categoria: Category,
                   val descricao: String,
                   val id: Int,
                   val nome: String,
                   val precoDe: Double,
                   val precoPor: Double,
                   val urlImagem: String): Parcelable