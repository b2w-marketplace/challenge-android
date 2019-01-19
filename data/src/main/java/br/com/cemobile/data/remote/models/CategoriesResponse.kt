package br.com.cemobile.data.remote.models

import br.com.cemobile.domain.model.Category
import com.google.gson.annotations.SerializedName

data class CategoriesResponse(@SerializedName("data") val data: List<Category>)