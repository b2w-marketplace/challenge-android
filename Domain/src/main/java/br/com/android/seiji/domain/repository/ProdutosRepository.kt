package br.com.android.seiji.domain.repository

import br.com.android.seiji.domain.model.Produto
import io.reactivex.Observable

interface ProdutosRepository {
    fun getProdutos(): Observable<List<Produto>>
}