package br.com.dafle.alodjinha.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BannerResponse(val data: List<Banner>): Parcelable

@Parcelize
data class Banner(val id: Int, val linkUrl: String, val urlImagem: String): Parcelable