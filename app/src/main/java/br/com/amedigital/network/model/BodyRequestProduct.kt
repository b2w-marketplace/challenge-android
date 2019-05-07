package br.com.amedigital.network.model

import br.com.amedigital.model.Product
import com.google.gson.annotations.SerializedName

class BodyRequestProduct (@SerializedName("data") var data: ArrayList<Product>)