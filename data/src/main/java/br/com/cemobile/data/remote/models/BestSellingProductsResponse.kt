package br.com.cemobile.data.remote.models

import br.com.cemobile.domain.model.Product
import com.google.gson.annotations.SerializedName

data class BestSellingProductsResponse(@SerializedName("data") val data: List<Product>)