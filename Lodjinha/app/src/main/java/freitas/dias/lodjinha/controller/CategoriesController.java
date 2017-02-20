package freitas.dias.lodjinha.controller;


import freitas.dias.lodjinha.api.model.Categories;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesController extends BaseController{

    public void getListCategories() {
        Call<Categories> call = apiService().getListCategories();
        call.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                Categories categories= response.body();
                post(categories);
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                post(t);
            }
        });
    }
}


