package br.com.andremoreira.lodjinha.repository

class RemoteConstant {

    companion object {
        const val ACCEPT_JSON = "Accept: application/json"
        const val BANNER = "/banner"
        const val CATEGORY = "/categoria"
        const val PRODUCT_MORE_SALLERS = "/produto/maisvendidos"
        const val PRODUCT_BY_CATEGORY = "/produto"
        const val PRODUCT_BY_ID = "/produto/{produtoId}"
        const val QUERY_OFFSET = "offset"
        const val QUERY_LIMITE = "limite"
        const val QUERY_CATEGORY_ID = "categoriaId"
        const val PARAM_PRODUCT_ID = "produtoId"
        const val URL_BASE = "https://alodjinha.herokuapp.com"
    }
}