package br.com.amedigital.challenge_android.models.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "unused")
@Entity(primaryKeys = [("id")])
data class Categoria (
    val id: Int,
    val descricao: String,
    val urlImagem: String
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readInt(),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(descricao)
        writeString(urlImagem)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Categoria> = object : Parcelable.Creator<Categoria> {
            override fun createFromParcel(source: Parcel): Categoria = Categoria(source)
            override fun newArray(size: Int): Array<Categoria?> = arrayOfNulls(size)
        }
    }
}