package br.com.andrecouto.alodjinha.data.source.remote.service.response

import br.com.andrecouto.alodjinha.domain.model.lodjinha.Category
import com.google.gson.annotations.SerializedName


data class CategoriesResponse (@SerializedName("data") var data : List<Category>)
