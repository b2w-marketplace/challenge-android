package br.com.cemobile.data.local.features.banner

import br.com.cemobile.data.local.database.LodjinhaDao
import br.com.cemobile.data.local.errors.LocalDataNotFoundException
import br.com.cemobile.data.local.mapper.ToBanner
import br.com.cemobile.data.local.mapper.ToBannerEntity
import br.com.cemobile.domain.model.Banner
import br.com.cemobile.domain.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class BannerLocalDataSourceImpl(
    private val dao: LodjinhaDao,
    private val ioContext: CoroutineContext = Dispatchers.IO
) : BannerLocalDataSource {

    override suspend fun getAllBanners(): Result<List<Banner>> = withContext(ioContext) {
        val entities = dao.banners()
        return@withContext if (entities.isNotEmpty()) {
            val banners = entities.map { ToBanner(it) }
            Result.Success(banners)
        } else {
            Result.Error(LocalDataNotFoundException())
        }
    }

    override suspend fun saveBanners(banners: List<Banner>) {
        if (banners.isEmpty()) {
            Timber.e("Não há banners para salvar no database")
            return
        }

        val entities = banners.map { ToBannerEntity(it) }

        withContext(ioContext) {
            dao.saveBanners(entities)
        }
    }

    override suspend fun deleteAllBanners() = withContext(ioContext) {
        dao.deleteAllBanners()
    }

}