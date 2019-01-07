package br.com.android.seiji.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import br.com.android.seiji.cache.db.CacheConfigConstants

@Entity(tableName = CacheConfigConstants.TABLE_NAME)
data class CacheConfig(
    @PrimaryKey(autoGenerate = true)
    var id: Int = -1,
    var lastCacheTime: Long
)