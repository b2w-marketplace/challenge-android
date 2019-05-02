package br.com.amedigital.challenge_android.mappers

import br.com.amedigital.challenge_android.models.network.GetBannersResponse

class GetBannersResponseMapper  : NetworkResponseMapper<GetBannersResponse> {
    override fun onLastPage(response: GetBannersResponse): Boolean {
        return true
    }
}