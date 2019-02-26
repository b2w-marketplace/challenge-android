package com.bryanollivie.lojinha

import com.bryanollivie.lojinha.data.model.BannerLoja
import com.bryanollivie.lojinha.data.model.ReturnBase
import com.bryanollivie.lojinha.data.remote.ServiceApi
import com.bryanollivie.lojinha.data.remote.ServiceImpl
import org.junit.Assert.assertNotNull
import org.junit.Test

class BannerTest {

    private val mApi = ServiceImpl()

    @Test
    fun buscaTodosOsBanners() {

        mApi.bannerFindAll(object : ServiceApi.ServiceCallback<ReturnBase> {
            override fun onLoaded(return_service: ReturnBase) {

                val banner = BannerLoja.toObject(return_service.data!!.get(0))
                assertNotNull(banner)
            }
        })
    }

}
