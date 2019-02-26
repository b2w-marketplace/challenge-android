package br.com.andrecouto.alodjinha.data.source.remote.service.response

import br.com.andrecouto.alodjinha.domain.model.lodjinha.Product
import com.google.gson.annotations.SerializedName

data class ProductsResponse (@SerializedName("data") var data: List<Product>)