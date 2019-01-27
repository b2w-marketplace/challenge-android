package com.sumiya.olodjinha.model

import java.io.Serializable

class ProductModel(var categoria: CategoryModel,
                   var descricao: String,
                   var id: Int,
                   var nome: String,
                   var precoDe: Number,
                   var precoPor: Number,
                   var urlImagem: String) : Serializable