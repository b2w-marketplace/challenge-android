package com.bryanollivie.lojinha

import com.bryanollivie.lojinha.data.model.Produto
import com.bryanollivie.lojinha.data.model.ReturnBase
import com.bryanollivie.lojinha.data.remote.ServiceApi
import com.bryanollivie.lojinha.data.remote.ServiceImpl
import org.junit.Test

import org.junit.Assert.*

class ProdutoTest {

    private val mApi = ServiceImpl()

    @Test
    fun buscaProdutoPorCategoria() {

        mApi.produtoFindByCategoria(2, object : ServiceApi.ServiceCallback<ReturnBase> {
            override fun onLoaded(return_service: ReturnBase) {

                val produto = Produto.toObject(return_service.data!!.get(0))
                assertNotNull(produto)
            }
        })
    }

    @Test
    fun buscaProdutoPorId() {

        mApi.produtoFindById(2, object : ServiceApi.ServiceCallback<Produto> {
            override fun onLoaded(return_service: Produto) {

                val produto = return_service
                assertNotNull(produto)
                assertTrue(produto.id != 0)
                assertNotNull(produto.nome!!.length > 0)
            }
        })
    }


    @Test
    fun buscaProdutosMaisVendidos() {

        mApi.produtoMaisVendidos(object : ServiceApi.ServiceCallback<ReturnBase> {
            override fun onLoaded(return_service: ReturnBase) {

                val produtos = Produto.toObject(return_service.data!!.get(0))
                assertNotNull(produtos)
            }
        })
    }


    @Test
    fun reservarProduto() {

        mApi.reservarProdutoById(2, object : ServiceApi.ServiceCallback<ReturnBase> {
            override fun onLoaded(return_service: ReturnBase) {

                val msg = return_service.result
                assertNotNull(msg)
                assertEquals("sucess", msg)
            }
        })
    }
}
