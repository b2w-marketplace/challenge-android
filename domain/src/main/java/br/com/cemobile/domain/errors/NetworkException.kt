package br.com.cemobile.domain.errors

class NetworkException : Exception {
    constructor(cause: Throwable?) : super(cause)
    constructor(message: String? = null,
                cause: Throwable? = null,
                enableSuppression: Boolean = false,
                writableStackTrace: Boolean = false
    ) : super(message, cause, enableSuppression, writableStackTrace)
}