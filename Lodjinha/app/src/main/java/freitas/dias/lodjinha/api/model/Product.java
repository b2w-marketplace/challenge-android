package freitas.dias.lodjinha.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Product implements Parcelable {

    @SerializedName("id")
    private int id;

    @SerializedName("nome")
    private String name;

    @SerializedName("urlImagem")
    private String urlImage;

    @SerializedName("descricao")
    private String description;

    @SerializedName("precoDe")
    private double priceOf;

    @SerializedName("precoPor")
    private double priceFor;

    @SerializedName("categoria")
    private Category category;

    public Product() {
    }


    protected Product(Parcel in) {
        id = in.readInt();
        name = in.readString();
        urlImage = in.readString();
        description = in.readString();
        priceOf = in.readDouble();
        priceFor = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(urlImage);
        dest.writeString(description);
        dest.writeDouble(priceOf);
        dest.writeDouble(priceFor);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPriceOf() {
        return priceOf;
    }

    public void setPriceOf(double priceOf) {
        this.priceOf = priceOf;
    }

    public double getPriceFor() {
        return priceFor;
    }

    public void setPriceFor(double priceFor) {
        this.priceFor = priceFor;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
