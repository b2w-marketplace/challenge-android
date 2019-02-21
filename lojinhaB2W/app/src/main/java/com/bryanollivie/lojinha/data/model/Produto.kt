package com.bryanollivie.lojinha.data.model

import com.google.gson.internal.LinkedTreeMap

class Produto(

    var categoria: Categoria? = null,
    var descricao: String? = "",
    var id: Int? = 0,
    var nome: String? = "",
    var precoDe: Double? = 0.0,
    var precoPor: Double? = 0.0,
    var urlImagem: String? = ""

) {
    companion object {

        fun toObject(itemModel: Any): LinkedTreeMap<*, *> {
            val getrow = itemModel
            val t = getrow as LinkedTreeMap<*, *>
            return t
        }

    }
}
