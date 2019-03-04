package br.com.andrecouto.alodjinha.domain.mapper

import br.com.andrecouto.alodjinha.domain.model.response.DomainErrorException
import br.com.andrecouto.alodjinha.domain.model.response.ErrorModel
import br.com.andrecouto.alodjinha.domain.model.response.ErrorStatus
import br.com.andrecouto.alodjinha.util.Mockable
import javax.inject.Inject

/**
 * A util class that generate an instance of [ErrorModel] with happened [Throwable]
 */
@Mockable
class DomainErrorUtil @Inject constructor() {

    /**
     * Generate an instance of [ErrorModel] with happened [Throwable]
     * @param t happened [Throwable]
     *
     * @return rentuns an instance of [ErrorModel]
     */
    fun getErrorModel(t: Throwable?): ErrorModel {
        if (t is DomainErrorException)
            return t.errorModel

        // if response was successful but no data received
        if (t is NullPointerException) {
            return ErrorModel(ErrorStatus.EMPTY_RESPONSE)
        }

        // something happened that we are not make our self ready for it
        return ErrorModel(ErrorStatus.NOT_DEFINED)
    }
}

