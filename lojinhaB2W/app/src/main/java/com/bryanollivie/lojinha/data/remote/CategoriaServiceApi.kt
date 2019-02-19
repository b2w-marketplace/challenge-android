
package com.bryanollivie.lojinha.data.remote
import com.bryanollivie.lojinha.data.model.ReturnBase

interface CategoriaServiceApi {

    interface ServiceCallback<T> {

        fun onLoaded(return_service: T)
    }

    fun findAll(callback: ServiceCallback<ReturnBase>)
}

