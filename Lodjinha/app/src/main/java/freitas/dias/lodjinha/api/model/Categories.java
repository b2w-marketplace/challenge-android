package freitas.dias.lodjinha.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Categories {

    @SerializedName("data")
    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }
}
