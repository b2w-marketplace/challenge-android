package com.b2w.lodjinha.data

import android.annotation.SuppressLint
import android.content.Context
import android.os.Parcelable
import android.support.annotation.StringRes
import com.b2w.lodjinha.R
import kotlinx.android.parcel.Parcelize

enum class Status { SUCCESS, ERROR }

data class Resource<out T>(val exception: ResourceException?, val status: Status, val data: T?) {
    companion object {
        fun <T> success(data: T?) = Resource(null, Status.SUCCESS, data)
        fun error(exception: ResourceException?) =
            Resource(exception, Status.ERROR, null)
    }
}

@SuppressLint("ParcelCreator")
@Parcelize
data class ResourceException(
    @StringRes var title: Int = R.string.default_error_title,
    @StringRes var msg: Int = R.string.default_error_message,
    val code: Int? = null
) : Parcelable {

    fun errorTitle(context: Context): String =
            context.getString(title)

    fun errorMessage(context: Context): String =
            context.getString(msg)

}