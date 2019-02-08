package com.caio.lodjinha.repository.remote.banner

import javax.inject.Inject

class BannerRemoteRep @Inject constructor(private val bannerREST: BannerREST) {

    fun getBanner() = bannerREST.getBanner()
}