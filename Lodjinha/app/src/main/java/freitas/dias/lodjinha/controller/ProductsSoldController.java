package freitas.dias.lodjinha.controller;


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
                Products products = response.body();
                post(products);
            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {
                post(t);
            }
        });
    }
}
