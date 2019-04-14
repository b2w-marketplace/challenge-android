package com.abmm.b2w.alodjinha.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("nome")
    private String name;

    @Expose
    @SerializedName("urlImagem")
    private String pictUrl;

    @Expose
    @SerializedName("descricao")
    private String description;

    @Expose
    @SerializedName("precoDe")
    private double originalPrice;

    @Expose
    @SerializedName("precoPor")
    private double sellingPrice;

    @Expose
    @SerializedName("categoria")
    private Category category;

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
}