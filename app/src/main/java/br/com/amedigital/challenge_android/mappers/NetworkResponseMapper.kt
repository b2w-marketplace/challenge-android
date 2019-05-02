package br.com.amedigital.challenge_android.mappers

import br.com.amedigital.challenge_android.models.NetworkResponseModel

interface NetworkResponseMapper <in FROM : NetworkResponseModel> {
    fun onLastPage(response: FROM): Boolean
}