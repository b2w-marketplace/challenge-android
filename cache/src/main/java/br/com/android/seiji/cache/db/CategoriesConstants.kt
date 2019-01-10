package br.com.android.seiji.cache.db

object CategoriesConstants {

    const val TABLE_NAME = "categories"

    const val COLUMN_CATEGORY_ID = "category_id"
    const val COLUMN_DESCRICAO = "category_descricao"
    const val COLUMN_URL_IMAGEM = "category_url_imagem"

    const val QUERY_CATEGORIES = "SELECT * FROM $TABLE_NAME"
    const val DELETE_CATEGORIES = "DELETE FROM $TABLE_NAME"
}