package br.com.amedigital.challenge_android.models.network

data class ErrorEnvelope(
    val status_code: Int,
    val status_message: String,
    val success: Boolean
)