package br.com.douglas.fukuhara.lodjinha.presenter.helper

import br.com.douglas.fukuhara.lodjinha.network.vo.ProductDataVo
import br.com.douglas.fukuhara.lodjinha.network.vo.ResultVo
import br.com.douglas.fukuhara.lodjinha.utils.TestUtils

object ProductDetailPresenterHelper {

    fun getProductObj(): ProductDataVo {
        return TestUtils.fromJsonToObj("json/product_detail.json", ProductDataVo::class.java)
    }

    fun getSuccessResponseWhenReserving(): ResultVo {
        return TestUtils.fromJsonToObj("json/reserving_success_result.json", ResultVo::class.java)
    }

    fun getFailureResponseWithMessageWhenReserving(): ResultVo {
        return TestUtils.fromJsonToObj("json/reserving_failure_result_with_message.json", ResultVo::class.java)
    }

    fun getFailureResponseWithoutMessageWhenReserving(): ResultVo {
        return TestUtils.fromJsonToObj("json/reserving_failure_result_without_message.json", ResultVo::class.java)
    }
}