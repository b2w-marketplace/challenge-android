package com.danilodequeiroz.lodjinha

import com.danilodequeiroz.lodjinha.common.util.toBRLMoneyString
import com.danilodequeiroz.lodjinha.home.domain.BannerViewModel
import com.danilodequeiroz.lodjinha.home.domain.ProductCategoryViewModel
import com.danilodequeiroz.lodjinha.home.domain.ProductViewModel
import com.danilodequeiroz.lodjinha.productdetail.domain.DetailedProducViewModel
import com.danilodequeiroz.webapi.model.banner.Banner
import com.danilodequeiroz.webapi.model.banner.BannersPayload
import com.danilodequeiroz.webapi.model.category.ProductCategoriesPayload
import com.danilodequeiroz.webapi.model.category.ProductCategory
import com.danilodequeiroz.webapi.model.product.BestSellingProductsPayload
import com.danilodequeiroz.webapi.model.product.Product
import com.danilodequeiroz.webapi.model.product.ProductsPayload

fun productPOJOmodel() =
    Product(1,  categoryPOJOModel(), "xbox", 2000, 1500.0, "lorem ispun", "localhost:8080")

fun bannersPOJOmodel() =
    BannersPayload(listOf(Banner(1,  "localhost:8080","localhost:8080")))

fun bestSellingsPOJOmodel() =
    BestSellingProductsPayload(listOf(productPOJOmodel()))

fun categoriesPOJOmodel() =
    ProductCategoriesPayload(listOf(categoryPOJOModel()))

fun productsListPOJOmodel() =
    ProductsPayload(0,listOf(productPOJOmodel()))

fun bannerViewModelFrom(banner:Banner) =
    BannerViewModel(banner.linkUrl,banner.urlImagem)


fun detailedProductViewModelFrom(product:Product) =
    DetailedProducViewModel(product.id,product.categoria?.id,product.categoria?.descricao,product.nome,product.descricao,product.precoDe?.toBRLMoneyString(),product.precoPor?.toBRLMoneyString(),product.urlImagem)


fun productViewModelFrom(product:Product) =
    ProductViewModel(product.id,product.categoria?.id,product.nome,product.precoDe?.toBRLMoneyString(),product.precoPor?.toBRLMoneyString(),product.urlImagem)
fun categoryViewModelFrom(category:ProductCategory) =
    ProductCategoryViewModel(category.id,category.descricao,category.urlImagem)

fun categoryPOJOModel() = ProductCategory(1,"localhost","cat")