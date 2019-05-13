package br.com.douglas.fukuhara.lodjinha.presenter.helper

import br.com.douglas.fukuhara.lodjinha.network.vo.BannerVo
import br.com.douglas.fukuhara.lodjinha.network.vo.CategoryVo
import br.com.douglas.fukuhara.lodjinha.network.vo.ProductBestSellerVo
import br.com.douglas.fukuhara.lodjinha.utils.TestUtils

object HomePresenterHelper {

    fun getBannerData(): BannerVo {
        return TestUtils.fromJsonToObj("json/banner_with_three_content.json", BannerVo::class.java)
    }

    fun getCategoryData(): CategoryVo {
        return TestUtils.fromJsonToObj("json/category_list_with_five_content.json", CategoryVo::class.java)
    }

    fun getBestSellingData(): ProductBestSellerVo {
        return TestUtils.fromJsonToObj("json/best_selling_products_with_five_content.json", ProductBestSellerVo::class.java)

    }
}