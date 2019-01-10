package br.com.android.seiji.data.model

data class ProductEntity(
    val id: Int, val nome: String, val descricao: String,
    val precoDe: Double, val precoPor: Double, val urlImagem: String,
    val category: CategoryEntity
)