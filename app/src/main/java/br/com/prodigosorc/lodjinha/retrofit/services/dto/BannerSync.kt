package br.com.prodigosorc.lodjinha.retrofit.services.dto

import br.com.prodigosorc.lodjinha.models.Banner


class BannerSync {
    private val data: List<Banner> = mutableListOf()

    fun getData(): List<Banner> = data
}