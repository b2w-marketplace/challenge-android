package br.com.cemobile.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HomeData(
    val banners: List<Banner>,
    val categories: List<Category>,
    val bestSellingProducts: List<Product>
) : Parcelable {

    val isNotEmpty: Boolean
        get() = banners.isNotEmpty() && categories.isNotEmpty() && bestSellingProducts.isNotEmpty()

}