package br.com.amedigital.challenge_android.models.network

import br.com.amedigital.challenge_android.models.NetworkResponseModel
import br.com.amedigital.challenge_android.models.entity.Banner
import br.com.amedigital.challenge_android.models.entity.Categoria

data class GetCategoriasResponse(
    val data: List<Categoria>
) : NetworkResponseModel