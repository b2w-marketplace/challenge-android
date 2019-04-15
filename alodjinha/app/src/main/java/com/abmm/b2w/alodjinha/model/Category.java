package com.abmm.b2w.alodjinha.model;

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
    String description;

    @Expose
    @SerializedName("urlImagem")
    String pictUrl;

    /* Getters */
    public int getId() { return id; }

    public String getDescription() { return description; }

    public String getPictUrl() { return pictUrl; }

    /* Setters */
    public void setId(int id) { this.id = id; }

    public void setDescription(String description) { this.description = description; }

    public void setPictUrl(String pictUrl) { this.pictUrl = pictUrl; }
}
