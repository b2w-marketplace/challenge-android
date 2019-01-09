package br.com.android.seiji.domain.repository

import br.com.android.seiji.domain.model.Category
import io.reactivex.Observable

interface CategoryRepository {
    fun getCategories(): Observable<List<Category>>
}