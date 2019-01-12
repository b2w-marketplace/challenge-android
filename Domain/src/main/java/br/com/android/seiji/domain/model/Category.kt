package br.com.android.seiji.domain.model

import java.io.Serializable


data class Category(
    val id: Int, val descricao: String, val urlImagem: String
) : Serializable