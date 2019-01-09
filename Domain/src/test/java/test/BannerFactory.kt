package test

import br.com.android.seiji.domain.model.Banner

object BannerFactory {

    private fun makeBanner(): Banner {
        return Banner(
            DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString()
        )
    }

    fun makeBannerList(count: Int): List<Banner> {
        val bannerList = mutableListOf<Banner>()
        repeat(count) {
            bannerList.add(makeBanner())
        }
        return bannerList
    }

}