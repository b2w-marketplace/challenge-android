package com.abmm.b2w.alodjinha.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Category {

    @Expose
    @SerializedName("id")
    int id;

    @Expose
    @SerializedName("descricao")
    String name;

    @Expose
    @SerializedName("urlImagem")
    String pictUrl;

    /* Getters */
    public int getId() { return id; }

    public String getName() { return name; }

    public String getPictUrl() { return pictUrl; }

    /* Setters */
    public void setId(int id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setPictUrl(String pictUrl) { this.pictUrl = pictUrl; }

    @NonNull
    @Override
    public String toString() {
        return "Category{" +
                "id=" + this.id +
                ", name=" + this.name +
                ", pictUrl=" + this.pictUrl +
                "";
    }
}
