package marcus.com.br.b2wtest.model.data

import com.squareup.moshi.Json

data class ProductResult(
    @field:Json(name = "data") val productList: List<ProductData>
)

data class ProductData(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "nome") val name: String,
    @field:Json(name = "urlImagem") val urlImage: String,
    @field:Json(name = "descricao") val description: String,
    @field:Json(name = "precoDe") val fromPrice: Double,
    @field:Json(name = "precoPor") val toPrice: Double
)