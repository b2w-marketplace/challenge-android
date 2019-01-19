package br.com.cemobile.lodjinha.ui.base.errors

sealed class NetworkingIssue : Throwable() {
    object InternetUnreachable : NetworkingIssue()
    object OperationTimeout : NetworkingIssue()
    object ConnectionSpike : NetworkingIssue()
}