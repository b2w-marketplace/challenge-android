package br.com.bsavoini.lodjinha.catalog;

import br.com.bsavoini.lodjinha.api.RetrofitInstance;
import br.com.bsavoini.lodjinha.api.model.ProductsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatalogInteractorImpl implements CatalogContract.CatalogInteractor {

    @Override
    public void requestProducts(int categoryId, int offset, int limit, final ProductsRequestListener listener) {
        Call<ProductsResponse> productsResponseCall = RetrofitInstance.getLodjinhaService().requestProducts(offset, limit, categoryId);

        productsResponseCall.enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listener.onProductRequestSuccessful(response.body().getTotal(), response.body().getProductsArr());
                } else {
                    listener.onProductRequestFail();
                }
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                listener.onProductRequestFail();
            }
        });
    }
}
