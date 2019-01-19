package br.com.cemobile.data.remote.models

import br.com.cemobile.domain.model.Banner
import com.google.gson.annotations.SerializedName

data class BannerResponse(@SerializedName("data") val data: List<Banner>)