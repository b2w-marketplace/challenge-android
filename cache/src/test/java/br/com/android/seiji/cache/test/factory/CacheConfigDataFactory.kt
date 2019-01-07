package br.com.android.seiji.cache.test.factory

import br.com.android.seiji.cache.model.CacheConfig

object CacheConfigDataFactory {

    fun makeCacheConfig(): CacheConfig {
        return CacheConfig(DataFactory.randomInt(), DataFactory.randomLong())
    }
}