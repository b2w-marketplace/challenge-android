package freitas.dias.lodjinha.api.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Banner implements Parcelable{

    @SerializedName("id")
    private int id;

    @SerializedName("urlImagem")
    private String urlImage;

    @SerializedName("linkUrl")
    private String linkImage;

    public Banner() {
    }

    protected Banner(Parcel in) {
        id = in.readInt();
        urlImage = in.readString();
        linkImage = in.readString();
    }

    public static final Creator<Banner> CREATOR = new Creator<Banner>() {
        @Override
        public Banner createFromParcel(Parcel in) {
            return new Banner(in);
        }

        @Override
        public Banner[] newArray(int size) {
            return new Banner[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(urlImage);
        parcel.writeString(linkImage);
    }
}
