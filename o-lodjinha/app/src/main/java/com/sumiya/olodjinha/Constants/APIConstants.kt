package com.sumiya.olodjinha.Constants

class APIConstants {
    companion object {
        const val API_URL = "https://alodjinha.herokuapp.com/"

        //Endpoint
        const val BANNER_ENDPOINT = "banner"
        const val CATEGORY_ENDPOINT = "categoria"
        const val PRODUCT_ENDPOINT = "produto"
        const val BEST_SELLER_ENDPOINT = "/maisvendidos"

        //Path
        const val PRODUCT_ID_PATH = "produtoId"

        //Query
        const val OFFSET_QUERY = "offset"
        const val LIMIT_QUERY = "limit"
        const val CATEGORY_ID_QUERY = "categoriaId"
    }
}
