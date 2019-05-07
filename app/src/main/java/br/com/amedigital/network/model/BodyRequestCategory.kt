package br.com.amedigital.network.model

import br.com.amedigital.model.Category
import com.google.gson.annotations.SerializedName

class BodyRequestCategory (@SerializedName("data") var data: ArrayList<Category>)