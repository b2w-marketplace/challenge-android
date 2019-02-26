package br.com.andrecouto.alodjinha.domain.model.response

/**
 * base sealed class for handling UseCase responses in [BaseUseCase]
 * @see [BaseUseCase]
 */
sealed class UseCaseResponse<out T>

sealed class UseCaseResponseNothing

/**
 * Wrapper for success response of repository calls
 */
data class SuccessResponse<out T>(val value: T): UseCaseResponse<T>()

data class SuccessResponseNothing<out T>(val value: T): UseCaseResponseNothing()

/**
 * Wrapper for error response of repository calls
 */
data class ErrorResponse<out T>(val error: ErrorModel): UseCaseResponse<T>()

data class ErrorResponseNothing<out T>(val value: T): UseCaseResponseNothing()