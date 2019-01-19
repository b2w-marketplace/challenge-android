package br.com.cemobile.domain

sealed class FetchStrategy {

    object FromPrevious : FetchStrategy()
    object ForceUpdate : FetchStrategy()

}