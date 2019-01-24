package com.example.b2w.model

import android.os.Parcel
import android.os.Parcelable

data class Product(
    var id: Long?,
    var nome: String?,
    var precoDe: Double?,
    var precoPor: Double?,
    var urlImagem: String?,
    var categoria: Category?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readParcelable(Category::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(nome)
        parcel.writeValue(precoDe)
        parcel.writeValue(precoPor)
        parcel.writeString(urlImagem)
        parcel.writeParcelable(categoria, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}