package br.com.android.seiji.remote

import br.com.android.seiji.data.model.CategoryEntity
import br.com.android.seiji.data.repository.category.CategoriesRemote
import br.com.android.seiji.remote.mapper.CategoriesResponseModelMapper
import br.com.android.seiji.remote.service.LodjinhaService
import io.reactivex.Flowable
import javax.inject.Inject

class CategoriesRemoteImpl @Inject constructor(
    private val service: LodjinhaService,
    private val mapper: CategoriesResponseModelMapper
) : CategoriesRemote {

    override fun getCategories(): Flowable<List<CategoryEntity>> {
        return service.getCategories()
            .map { it ->
                it.data.map {
                    mapper.mapFromModel(it)
                }
            }
    }
}