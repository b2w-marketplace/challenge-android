package com.abmm.b2w.alodjinha.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("descricao")
    private String description;

    @Expose
    @SerializedName("urlImagem")
    private String pictUrl;

    /* Getters */
    public int getId() { return id; }

    public String getDescription() { return description; }

    public String getPictUrl() { return pictUrl; }

    /* Setters */
    public void setId(int id) { this.id = id; }

    public void setDescription(String description) { this.description = description; }

    public void setPictUrl(String pictUrl) { this.pictUrl = pictUrl; }
}
