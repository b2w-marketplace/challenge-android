package br.com.prodigosorc.lodjinha.retrofit.services.dto

import br.com.prodigosorc.lodjinha.models.Categoria

class CategoriaSync {
    private val data: List<Categoria> = mutableListOf()

    fun getData(): List<Categoria> = data
}