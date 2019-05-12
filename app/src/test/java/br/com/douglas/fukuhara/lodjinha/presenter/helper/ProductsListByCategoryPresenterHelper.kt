package br.com.douglas.fukuhara.lodjinha.presenter.helper

import br.com.douglas.fukuhara.lodjinha.network.vo.ProductVo
import br.com.douglas.fukuhara.lodjinha.utils.TestUtils

object ProductsListByCategoryPresenterHelper {

    fun getTwelveProductsWhenQueryWithOffsetZeroLimitTwelve(): ProductVo {
        return TestUtils.fromJsonToObj("json/product_list_fetch_01_product_20.json", ProductVo::class.java)
    }

    fun getZeroProductWhenQueryWithOffsetTwelveLimitTwelve(): ProductVo {
        return TestUtils.fromJsonToObj("json/product_list_fetch_02_product_0.json", ProductVo::class.java)
    }

    fun getOnlyOneProductWhenQueryWithOffsetZeroLimitTwelve(): ProductVo {
        return TestUtils.fromJsonToObj("json/product_list_fetch_01_product_01.json", ProductVo::class.java)
    }
}