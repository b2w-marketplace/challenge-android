package br.com.amedigital.challenge_android.models.network

import br.com.amedigital.challenge_android.models.NetworkResponseModel
import br.com.amedigital.challenge_android.models.entity.Banner

data class GetBannersResponse(
    val data: List<Banner>
) : NetworkResponseModel