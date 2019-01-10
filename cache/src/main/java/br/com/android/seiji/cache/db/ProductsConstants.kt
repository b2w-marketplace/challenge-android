package br.com.android.seiji.cache.db

object ProductsConstants {

    const val TABLE_NAME = "products"

    const val COLUMN_PRODUCT_ID = "product_id"
    const val COLUMN_NOME = "nome"
    const val COLUMN_DESCRICAO = "descricao"
    const val COLUMN_PRECO_DE = "preco_de"
    const val COLUMN_PRECO_POR = "preco_por"
    const val COLUMN_URL_IMAGEM = "url_imagem"
    const val COLUMN_CATEGORIA = "categoria"

    const val QUERY_PRODUCTS = "SELECT * FROM $TABLE_NAME"
    const val DELETE_PRODUCTS = "DELETE FROM $TABLE_NAME"
}