package br.com.bsavoini.lodjinha.home;

import br.com.bsavoini.lodjinha.api.RetrofitInstance;
import br.com.bsavoini.lodjinha.api.model.BannersResponse;
import br.com.bsavoini.lodjinha.api.model.CategoriesResponse;
import br.com.bsavoini.lodjinha.api.model.ProductsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeInteractorImpl implements HomeContract.HomeInteractor {

    @Override
    public void requestBanners(final BannerRequestListener listener) {
        Call<BannersResponse> bannersResponseCall = RetrofitInstance.getLodjinhaService().requestBanner();
        bannersResponseCall.enqueue(new Callback<BannersResponse>() {
            @Override
            public void onResponse(Call<BannersResponse> call, Response<BannersResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listener.onBannerRequestSuccessful(response.body().getBannersArr());
                } else {
                    listener.onBannerRequestFail();
                }
            }

            @Override
            public void onFailure(Call<BannersResponse> call, Throwable t) {
                listener.onBannerRequestFail();
            }
        });
    }

    @Override
    public void requestCategories(final CategoriesRequestListener listener) {
        Call<CategoriesResponse> categoriesResponseCall = RetrofitInstance.getLodjinhaService().requestCategories();
        categoriesResponseCall.enqueue(new Callback<CategoriesResponse>() {
            @Override
            public void onResponse(Call<CategoriesResponse> call, Response<CategoriesResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listener.onCategoriesRequestSuccessful(response.body().getCategoriesArr());
                } else {
                    listener.onCategoriesRequestFail();
                }
            }

            @Override
            public void onFailure(Call<CategoriesResponse> call, Throwable t) {
                listener.onCategoriesRequestFail();
            }
        });
    }

    @Override
    public void requestBestSellers(final BestSellersRequestListener listener) {
        Call<ProductsResponse> productsResponseCall = RetrofitInstance.getLodjinhaService().requestBestSellers();
        productsResponseCall.enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listener.onBestSellersRequestSuccessful(response.body().getProductsArr());
                } else {
                    listener.onBestSellersRequestFail();
                }
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                listener.onBestSellersRequestFail();
            }
        });
    }
}
