package br.com.andrecouto.alodjinha.data.mapper

import br.com.andrecouto.alodjinha.domain.model.response.DomainErrorException
import br.com.andrecouto.alodjinha.domain.model.response.ErrorModel
import br.com.andrecouto.alodjinha.domain.model.response.ErrorStatus
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class CloudErrorMapper @Inject constructor() {

    fun mapToDomainErrorException(throwable: Throwable?): Throwable? {
        val errorModel: ErrorModel? = when (throwable) {

            is HttpException -> {
                // handle UNAUTHORIZED situation (when token expired)
                if (throwable.code() == 401) {
                    ErrorModel(ErrorStatus.UNAUTHORIZED)
                } else {
                    getHttpError(throwable.response().errorBody())
                }
            }

            // handle api call timeout error
            is SocketTimeoutException -> {
                ErrorModel(ErrorStatus.TIMEOUT)
            }

            // handle connection error
            is IOException -> {
                ErrorModel(ErrorStatus.NO_CONNECTION)
            }
            else -> null
        }
        return errorModel?.let { DomainErrorException(it) } ?: throwable
    }

    private fun getHttpError(body: ResponseBody?): ErrorModel {
        return try {
            ErrorModel("error detail", body?.hashCode(), ErrorStatus.BAD_RESPONSE)
        } catch (e: Throwable) {
            e.printStackTrace()
            ErrorModel(ErrorStatus.NOT_DEFINED)
        }

    }
}