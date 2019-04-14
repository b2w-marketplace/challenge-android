package br.com.dafle.alodjinha.business

import br.com.dafle.alodjinha.service.HomeService
import br.com.dafle.alodjinha.util.defaultThread

class HomeBusinnes(private val service: HomeService) {

    fun fetchBanner() = service.fetchBanner().defaultThread()

    fun fetchCategory() = service.fetchCategory().defaultThread()
}