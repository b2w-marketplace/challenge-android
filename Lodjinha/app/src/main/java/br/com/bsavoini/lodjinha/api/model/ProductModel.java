package br.com.bsavoini.lodjinha.api.model;

import com.google.gson.annotations.SerializedName;

public class ProductModel {
    @SerializedName("id")
    private int id;

    @SerializedName("nome")
    private String name;

    @SerializedName("urlImagem")
    private String imageURL;

    @SerializedName("descricao")
    private String description;

    @SerializedName("precoDe")
    private Double originalPrice;

    @SerializedName("precoPor")
    private Double currentPrice;

    @SerializedName("categoria")
    private CategoryModel category;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getDescription() {
        return description;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public CategoryModel getCategory() {
        return category;
    }
}
