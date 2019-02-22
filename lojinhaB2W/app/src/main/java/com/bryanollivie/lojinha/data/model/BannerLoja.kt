package com.bryanollivie.lojinha.data.model

import com.google.gson.internal.LinkedTreeMap

data class BannerLoja(
    var id: Int? = 0,
    var linkUrl: String? = "",
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


