package marcus.com.br.b2wtest.model


enum class Status { SUCCESS, DEFAULT_ERROR, INTERNET_ERROR, NOT_FOUND }

data class Resource<out T>(
    val exception: Throwable?,
    val status: Status,
    val data: T?
) {
    companion object {
        fun <T> success(data: T?) = Resource(
            null,
            Status.SUCCESS,
            data
        )

        fun error(exception: Throwable?, status: Status = Status.DEFAULT_ERROR) =
            Resource(exception, status, null)
    }
}