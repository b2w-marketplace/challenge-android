package com.sumiya.olodjinha.Model

import java.io.Serializable

class ProductModel(val categoria: CategoryModel,
                   val descricao: String,
                   val id: Int,
                   val nome: String,
                   val precoDe: Number,
                   val precoPor: Number,
                   val urlImagem: String): Serializable