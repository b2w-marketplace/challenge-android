package com.sumiya.olodjinha.contracts.base

interface ViewBaseContract {
    fun hideLoading()

    fun handleError(t: Throwable)
}