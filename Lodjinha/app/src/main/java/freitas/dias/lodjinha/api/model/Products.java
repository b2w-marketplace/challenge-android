package freitas.dias.lodjinha.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Products {

    @SerializedName("data")
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }
}
