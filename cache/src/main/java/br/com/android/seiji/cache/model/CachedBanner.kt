package br.com.android.seiji.cache.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import br.com.android.seiji.cache.db.BannersConstants

@Entity(tableName = BannersConstants.TABLE_NAME)
data class CachedBanner(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = BannersConstants.COLUMN_BANNER_ID)
    var id: Int = -1,
    @ColumnInfo(name = BannersConstants.COLUMN_URL_IMAGE)
    var urlImagem: String,
    @ColumnInfo(name = BannersConstants.COLUMN_LINK_URL)
    var linkUrl: String
)
