package br.com.android.seiji.remote.model

import com.google.gson.annotations.SerializedName

class ProductModel(
        val id: Int, val nome: String, val descricao: String,
        val precoDe: Double, val precoPor: Double, val urlImagem: String,
        @SerializedName("categoria")
        val category: CategoryModel
)