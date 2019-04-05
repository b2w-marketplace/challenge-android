package br.com.andremoreira.lodjinha.repository.remote

data class ErrorResponse(
        var mensagem : String? = null
)

object ErrorConstant{
    const val CONNECTION_NOT : String = "Verifique sua conexão com a internet!"
    const val SERVICE_NOT : String = "Serviço não disponível!"
}