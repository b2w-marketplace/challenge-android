package br.com.b2w.desafio.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class LodjinhaResponse<T>(
    @SerializedName("data") val data: @RawValue T? = null,
    @SerializedName("offset") val resultCode: Int? = null,
    @SerializedName("total") val total: Int? = null,
    @SerializedName("result")  val result: String? = null,
    val message: String? = null
) : Parcelable