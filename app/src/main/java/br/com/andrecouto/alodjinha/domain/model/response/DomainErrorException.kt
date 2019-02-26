package br.com.andrecouto.alodjinha.domain.model.response

class DomainErrorException(val errorModel: ErrorModel): Throwable()