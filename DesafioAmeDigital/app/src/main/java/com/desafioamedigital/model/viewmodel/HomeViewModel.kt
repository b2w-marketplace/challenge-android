package com.desafioamedigital.model.viewmodel

import com.desafioamedigital.model.dto.BannerList
import com.desafioamedigital.model.dto.CategoryList
import com.desafioamedigital.model.dto.ProductList

data class HomeViewModel(
    val bannerList: BannerList,
    val categoryList: CategoryList,
    val productList: ProductList
)