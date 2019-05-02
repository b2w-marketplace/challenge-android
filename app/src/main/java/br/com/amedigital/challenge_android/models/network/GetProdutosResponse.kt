package br.com.amedigital.challenge_android.models.network

import br.com.amedigital.challenge_android.models.NetworkResponseModel
import br.com.amedigital.challenge_android.models.entity.Produto

data class GetProdutosResponse(
    val data: List<Produto>
) : NetworkResponseModel