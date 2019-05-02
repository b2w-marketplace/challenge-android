package com.abmm.b2w.alodjinha.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Product {

    @Expose
    @SerializedName("id")
    int id;

    @Expose
    @SerializedName("nome")
    String name;

    @Expose
    @SerializedName("urlImagem")
    String pictUrl;

    @Expose
    @SerializedName("descricao")
    String description;

    @Expose
    @SerializedName("precoDe")
    double originalPrice;

    @Expose
    @SerializedName("precoPor")
    double sellingPrice;

    @Expose
    @SerializedName("categoria")
    Category category;

    /* Getters */
    public int getId() { return id; }

    public String getName() { return name; }

    public String getPictUrl() { return pictUrl; }

    public String getDescription() { return description; }

    public double getOriginalPrice() { return originalPrice; }

    public double getSellingPrice() { return sellingPrice; }

    public Category getCategory() { return category; }

    /* Setters */
    public void setId(int id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setPictUrl(String pictUrl) { this.pictUrl = pictUrl; }

    public void setDescription(String description) { this.description = description; }

    public void setOriginalPrice(double originalPrice) { this.originalPrice = originalPrice; }

    public void setSellingPrice(double sellingPrice) { this.sellingPrice = sellingPrice; }

    public void setCategory(Category category) { this.category = category; }

    @NonNull
    @Override
    public String toString() {
        return "Product{" +
                "id=" + this.id +
                ", name=" + this.name +
                ", pictUrl=" + this.pictUrl +
                ", description=" + this.description +
                ", originalPrice=" + this.originalPrice +
                ", sellingPrice=" + this.sellingPrice +
                ", category=" + this.category +
                "}";
    }
}