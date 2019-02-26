package br.com.andrecouto.alodjinha.data.source.remote.service.response

import br.com.andrecouto.alodjinha.domain.model.lodjinha.Banner
import com.google.gson.annotations.SerializedName

data class BannerResponse (@SerializedName("data") var data : List<Banner>)
