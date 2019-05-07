package com.mmart.sidershopping.view.homepage;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.mmart.sidershopping.model.api.RetrofitClient;
import com.mmart.sidershopping.model.entity.Banner;
import com.mmart.sidershopping.model.entity.BannerResult;
import com.mmart.sidershopping.model.entity.BestSellerResult;
import com.mmart.sidershopping.model.entity.CategoriesResult;
import com.mmart.sidershopping.model.entity.Category;
import com.mmart.sidershopping.model.entity.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRepository {

    public MutableLiveData<List<Banner>> getBanner() {

        final MutableLiveData bannerList = new MutableLiveData<List<Banner>>();

        RetrofitClient.getInstance()
                .api()
                .getBanner()
                .enqueue(new Callback<BannerResult>() {
                    @Override
                    public void onResponse(Call<BannerResult> call, Response<BannerResult> response) {
                        bannerList.postValue(response.body().getResults());
                    }

                    @Override
                    public void onFailure(Call<BannerResult> call, Throwable t) {
                        String message = t.getMessage();
                        Log.d("failure", message);
                    }
                });

        return bannerList;
    }

    public MutableLiveData<List<Category>> getCategories() {

        final MutableLiveData categoriesList = new MutableLiveData<List<Category>>();

        RetrofitClient.getInstance()
                .api()
                .getCategories()
                .enqueue(new Callback<CategoriesResult>() {
                    @Override
                    public void onResponse(Call<CategoriesResult> call, Response<CategoriesResult> response) {
                        categoriesList.postValue(response.body().getResults());
                    }

                    @Override
                    public void onFailure(Call<CategoriesResult> call, Throwable t) {

                    }
                });

        return categoriesList;
    }

    public MutableLiveData<List<Product>> getBestSellers() {

        final MutableLiveData bestSellerList = new MutableLiveData<List<Product>>();

        RetrofitClient.getInstance()
                .api()
                .getBestSellers()
                .enqueue(new Callback<BestSellerResult>() {
                    @Override
                    public void onResponse(Call<BestSellerResult> call, Response<BestSellerResult> response) {

                        bestSellerList.postValue(response.body().getResults());
                    }

                    @Override
                    public void onFailure(Call<BestSellerResult> call, Throwable t) {

                    }
                });

        return bestSellerList;
    }
}
