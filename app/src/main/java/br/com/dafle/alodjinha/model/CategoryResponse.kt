package br.com.dafle.alodjinha.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryResponse(val data: List<Category>): Parcelable

@Parcelize
data class Category(val descricao: String, val id: Int, val urlImagem: String): Parcelable