package br.com.kakobotasso.lodjinha.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class DataCategoriaContainer {
    @SerializedName("data")
    private List<Categoria> dataCategoriaList = new ArrayList<>();

    public List<Categoria> getDataModelList() {
        return dataCategoriaList;
    }
}
