package marcus.com.br.b2wtest.model.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryResult(
    @field:Json(name = "data") val categoryList: List<CategoryData>
) : Parcelable

@Parcelize
data class CategoryData(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "descricao") val description: String,
    @field:Json(name = "urlImagem") val urlImage: String
) : Parcelable