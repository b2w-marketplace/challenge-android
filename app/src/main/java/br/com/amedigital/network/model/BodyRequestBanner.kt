package br.com.amedigital.network.model

import br.com.amedigital.model.Banner
import com.google.gson.annotations.SerializedName

class BodyRequestBanner (
    @SerializedName("data") var data: ArrayList<Banner>
)