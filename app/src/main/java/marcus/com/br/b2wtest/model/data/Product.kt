package marcus.com.br.b2wtest.model.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductResult(
    @field:Json(name = "data") val productList: List<ProductData>
) : Parcelable

@Parcelize
data class ProductData(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "nome") val name: String,
    @field:Json(name = "urlImagem") val urlImage: String,
    @field:Json(name = "descricao") val description: String,
    @field:Json(name = "precoDe") val fromPrice: Double,
    @field:Json(name = "precoPor") val toPrice: Double
) : Parcelable