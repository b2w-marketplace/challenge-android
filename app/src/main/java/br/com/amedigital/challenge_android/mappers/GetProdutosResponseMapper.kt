package br.com.amedigital.challenge_android.mappers

import br.com.amedigital.challenge_android.models.network.GetProdutosResponse

class GetProdutosResponseMapper  : NetworkResponseMapper<GetProdutosResponse> {
    override fun onLastPage(response: GetProdutosResponse): Boolean {
        return true
    }
}