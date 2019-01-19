package br.com.cemobile.lodjinha.ui.base.errors

import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object NetworkErrorHandler {

    fun checkIfNetworkError(throwable: Throwable): Boolean =
        isConnectionTimeout(throwable) ||
        noInternetAvailable(throwable) ||
        isRequestCanceled(throwable)

    fun mapToNetworkError(throwable: Throwable): Throwable {
        if (isConnectionTimeout(throwable)) return NetworkingIssue.OperationTimeout
        if (noInternetAvailable(throwable)) return NetworkingIssue.InternetUnreachable
        return NetworkingIssue.ConnectionSpike
    }

    private fun isRequestCanceled(throwable: Throwable): Boolean {
        return throwable is IOException
                && throwable.message?.contentEquals("Canceled") ?: false
    }

    private fun noInternetAvailable(throwable: Throwable): Boolean {
        return throwable is UnknownHostException
    }

    private fun isConnectionTimeout(throwable: Throwable): Boolean {
        return throwable is SocketTimeoutException
    }

}