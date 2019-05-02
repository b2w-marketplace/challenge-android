package br.com.amedigital.challenge_android.models.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "unused")
@Entity(primaryKeys = [("id")])
class Produto (
    val id: Int,
    val nome: String,
    val urlImagem: String,
    val descricao: String,
    val precoDe: Float,
    val precoPor: Float
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readInt(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readFloat(),
        source.readFloat()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(nome)
        writeString(urlImagem)
        writeString(descricao)
        writeFloat(precoDe)
        writeFloat(precoPor)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Produto> = object : Parcelable.Creator<Produto> {
            override fun createFromParcel(source: Parcel): Produto = Produto(source)
            override fun newArray(size: Int): Array<Produto?> = arrayOfNulls(size)
        }
    }
}