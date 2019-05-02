package br.com.amedigital.challenge_android.mappers

import br.com.amedigital.challenge_android.models.network.GetCategoriasResponse

class GetCategoriasResponseMapper  : NetworkResponseMapper<GetCategoriasResponse> {
    override fun onLastPage(response: GetCategoriasResponse): Boolean {
        return true
    }
}