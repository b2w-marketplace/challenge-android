package br.com.android.seiji.domain.repository

import br.com.android.seiji.domain.model.Categoria
import io.reactivex.Observable

interface CategoriasRepository {
    fun getCategorias(): Observable<List<Categoria>>
}