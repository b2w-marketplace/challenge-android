package br.com.amedigital.banner

import br.com.amedigital.model.Banner

interface BannerContract {
    interface View {
        fun setProgressIndicatorBanner(active: Boolean)

        fun showBanners(banners: ArrayList<Banner>)

        fun showErrorBanner(message : String)
    }

    interface BannerActionListener {
        fun loadBanners()
    }
}