package freitas.dias.lodjinha.controller;


import java.util.List;

import freitas.dias.lodjinha.api.model.Product;
import freitas.dias.lodjinha.api.model.Products;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsSoldController extends BaseController {

    public void getListCategoriesSold() {
        Call<Products> call = apiService().getListProductsSold();
        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                List<Product> categoryList = response.body().getProducts();
                post(categoryList);
            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {
                post(t);
            }
        });
    }
}
