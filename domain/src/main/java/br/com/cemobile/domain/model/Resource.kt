package br.com.cemobile.domain.model

/**
 * A generic class that holds a value with its loading status.
*/
open class Resource<out T>(
    val status: Status,
    val data: T? = null,
    val error: Throwable? = null
) {

    open val isEmpty: Boolean
        get() = data == null || (data is Collection<*> && data.isEmpty())

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Resource<*>

        if (status != other.status) return false
        if (data != other.data) return false
        if (error != other.error) return false

        return true
    }

    override fun hashCode(): Int {
        var result = status.hashCode()
        result = 31 * result + (data?.hashCode() ?: 0)
        result = 31 * result + (error?.hashCode() ?: 0)
        return result
    }

    companion object {
        fun <T> success(data: T?): Resource<T> = Resource(Status.SUCCESS, data)

        fun <T> success(): Resource<T> = Resource(Status.SUCCESS)

        fun <T> error(error: Throwable, data: T?): Resource<T> = Resource(Status.ERROR, data, error)

        fun <T> error(error: Throwable): Resource<T> = Resource(Status.ERROR, error = error)

        fun <T> loading(data: T?): Resource<T> = Resource(Status.LOADING, data)

        fun <T> loading(): Resource<T> = Resource(Status.LOADING)

        fun <T> none(): Resource<T> = Resource(Status.NONE)
    }

}