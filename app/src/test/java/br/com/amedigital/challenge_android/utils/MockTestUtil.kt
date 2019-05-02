package br.com.amedigital.challenge_android.utils

import br.com.amedigital.challenge_android.models.entity.Banner

class MockTestUtil {
    companion object {
        fun mockBannerList(): List<Banner> {
            val data = ArrayList<Banner>()
            data.add(Banner(1, "http://www.amedigital.com.br", "http://www.amedigital.com.br"))
            data.add(Banner(2, "http://www.submarino.com.br", "http://www.submarino.com.br"))
            data.add(Banner(3, "http://www.americanas.com.br", "http://www.americanas.com.br"))
            return data
        }

    }
}