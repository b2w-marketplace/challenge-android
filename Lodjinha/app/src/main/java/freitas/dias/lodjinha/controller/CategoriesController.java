package freitas.dias.lodjinha.controller;


import java.util.List;

import freitas.dias.lodjinha.api.model.Categories;
import freitas.dias.lodjinha.api.model.Category;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesController extends BaseController{

    public void getListCategories() {
        Call<Categories> call = apiService().getListCategories();
        call.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                List<Category> categoryList = response.body().getCategories();
                post(categoryList);
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                post(t);
            }
        });
    }
}


