package marcus.com.br.b2wtest.model.data

import com.squareup.moshi.Json

data class CategoryResult(
    @field:Json(name = "data") val categoryList: List<CategoryData>
)

data class CategoryData(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "descricao") val description: String,
    @field:Json(name = "urlImagem") val urlImage: String
)