package br.com.android.seiji.cache.db

object BannersConstants {

    const val TABLE_NAME = "banners"

    const val COLUMN_BANNER_ID = "banner_id"
    const val COLUMN_URL_IMAGE = "url_imagem"
    const val COLUMN_LINK_URL = "link_url"

    const val QUERY_BANNERS = "SELECT * FROM $TABLE_NAME"
    const val DELETE_BANNERS = "DELETE FROM $TABLE_NAME"
}