package com.danilodequeiroz.lodjinha.common.util

import android.content.Context
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.danilodequeiroz.lodjinha.home.domain.BannerViewModel
import com.danilodequeiroz.lodjinha.home.domain.BaseViewModel
import com.danilodequeiroz.lodjinha.home.domain.ProductCategoryViewModel
import com.danilodequeiroz.lodjinha.home.domain.ProductViewModel
import com.danilodequeiroz.lodjinha.common.presentation.ListState
import com.danilodequeiroz.webapi.model.banner.BannersPayload
import com.danilodequeiroz.webapi.model.category.ProductCategoriesPayload
import com.danilodequeiroz.webapi.model.product.BestSellingProductsPayload
import java.util.*
import android.text.Html
import android.os.Build
import android.text.Spanned
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.danilodequeiroz.lodjinha.R


fun MutableLiveData<ListState>.currentData(): MutableList<BaseViewModel> =
    this.value?.data?.toMutableList() ?: mutableListOf()

fun MutableLiveData<ListState>.didLoadedAllItems(): Boolean = this.value?.loadedAllItems ?: false

fun Number.toBRLMoneyString(): String {
    return "R$ %.2f".format(Locale("pt", "BR"), this.toFloat())
}

fun BannersPayload.bannersMapper(): MutableList<BannerViewModel> {
    return this.data?.map { banner ->
        BannerViewModel(
            banner?.linkUrl, banner?.urlImagem
        )
    }?.toMutableList() ?: mutableListOf()
}


fun ProductCategoriesPayload.categoriesMapper(): MutableList<ProductCategoryViewModel> {
    return this.data?.map { category ->
        ProductCategoryViewModel(
            category?.id, category?.descricao, category?.urlImagem
        )
    }?.toMutableList() ?: mutableListOf()
}


fun BestSellingProductsPayload.productsMapper(): MutableList<ProductViewModel> {
    return this.data?.map { product ->
        ProductViewModel(
            product.id,
            product.categoria?.id,
            product.nome,
            product.precoDe?.toBRLMoneyString(),
            product.precoPor?.toBRLMoneyString(),
            product.urlImagem
        )
    }?.toMutableList() ?: mutableListOf()
}

fun Context.openUrl(stringUrl: String) {
    val builder = CustomTabsIntent.Builder()
    builder.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary))
    builder.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark))
    val customTabsIntent = builder.build()
    customTabsIntent.launchUrl(this, Uri.parse(stringUrl))
}

@Suppress("deprecation")
fun String.fromHtml(): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(this)
    }
}