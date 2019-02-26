package com.bryanollivie.lojinha

import com.bryanollivie.lojinha.data.model.Categoria
import com.bryanollivie.lojinha.data.model.ReturnBase
import com.bryanollivie.lojinha.data.remote.ServiceApi
import com.bryanollivie.lojinha.data.remote.ServiceImpl
import org.junit.Assert.assertNotNull
import org.junit.Test

class CategoriaTest {

    private val mApi = ServiceImpl()

    @Test
    fun buscaTodasAsCategorias() {

        mApi.categoriaFindAll( object : ServiceApi.ServiceCallback<ReturnBase> {
            override fun onLoaded(return_service: ReturnBase) {

                val categoria = Categoria.toObject(return_service.data!!.get(0))
                assertNotNull(categoria)
            }
        })
    }

}
