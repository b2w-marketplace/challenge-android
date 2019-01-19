package br.com.cemobile.data.local.features.category

import br.com.cemobile.data.local.database.LodjinhaDao
import br.com.cemobile.data.local.errors.LocalDataNotFoundException
import br.com.cemobile.data.local.mapper.ToCategory
import br.com.cemobile.data.local.mapper.ToCategoryEntity
import br.com.cemobile.domain.model.Category
import br.com.cemobile.domain.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class CategoryLocalDataSourceImpl(
    private val dao: LodjinhaDao,
    private val ioContext: CoroutineContext = Dispatchers.IO
) : CategoryLocalDataSource {

    override suspend fun getCategories(): Result<List<Category>> = withContext(ioContext) {
        val entities = dao.categories()
        return@withContext if (entities.isNotEmpty()) {
            val categories = entities.map { ToCategory(it) }
            Result.Success(categories)
        } else {
            Result.Error(LocalDataNotFoundException())
        }
    }

    override suspend fun save(categories: List<Category>) {
        if (categories.isEmpty()) {
            Timber.e("Não há categories para salvar no database")
            return
        }

        val entities = categories.map { ToCategoryEntity(it) }

        withContext(ioContext) {
            dao.saveCategories(entities)
        }
    }

    override suspend fun deleteAll() = withContext(ioContext) {
        dao.deleteAllCategories()
    }
    
}