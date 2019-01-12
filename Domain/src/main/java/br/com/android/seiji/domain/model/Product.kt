package br.com.android.seiji.domain.model

import java.io.Serializable

data class Product(
    val id: Int, val nome: String, val descricao: String,
    val precoDe: Double, val precoPor: Double, val urlImagem: String,
    val category: Category
) : Serializable