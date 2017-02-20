package freitas.dias.lodjinha.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Banners {

    @SerializedName("data")
    private List<Banner> banners;

    public List<Banner> getBanners() {
        return banners;
    }
}
