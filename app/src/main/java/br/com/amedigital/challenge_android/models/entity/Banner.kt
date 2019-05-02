package br.com.amedigital.challenge_android.models.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "unused")
@Entity(primaryKeys = [("id")])
data class Banner(
    val id: Int,
    val urlImagem: String,
    val linkUrl: String
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readInt(),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(urlImagem)
        writeString(linkUrl)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Banner> = object : Parcelable.Creator<Banner> {
            override fun createFromParcel(source: Parcel): Banner = Banner(source)
            override fun newArray(size: Int): Array<Banner?> = arrayOfNulls(size)
        }
    }
}