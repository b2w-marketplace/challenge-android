package com.bryanollivie.lojinha.data.model

import com.bannerlayout.listener.BannerModelCallBack


class BannerLoja : BannerModelCallBack {

    var id: Int? = 0
    var title: String? = ""
    var linkUrl: String? = ""
    var urlImagem: String? = ""

    override val bannerUrl: String
        get() = linkUrl.toString()

    override val bannerTitle: String
        get() = title.toString()

    constructor(image: String) {
        this.urlImagem = image
    }

    constructor(image: String, title: String) {
        this.urlImagem = image
        this.title = title
    }

    constructor()
}
