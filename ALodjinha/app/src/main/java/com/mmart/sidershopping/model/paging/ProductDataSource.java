package com.mmart.sidershopping.model.paging;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import com.mmart.sidershopping.model.api.RetrofitClient;
import com.mmart.sidershopping.model.entity.BestSellerResult;
import com.mmart.sidershopping.model.entity.Product;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class ProductDataSource extends PageKeyedDataSource<Integer, Product> {


    public static final int OFFSET = 0;
    public int LIMIT = 20;
    int categoryId;


    public ProductDataSource(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Product> callback) {

        RetrofitClient.getInstance()
                .api()
                .getProducts(categoryId, OFFSET, LIMIT)
                .enqueue(new Callback<BestSellerResult>() {
                    @Override
                    public void onResponse(Call<BestSellerResult> call, Response<BestSellerResult> response) {
                        if (response.body() != null) {

                            callback.onResult(response.body().getResults(), null, OFFSET + 20);
                        }
                    }

                    @Override
                    public void onFailure(Call<BestSellerResult> call, Throwable t) {
                        String message = t.getMessage();
                        Log.d("failure", message);
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Product> callback) {

        LIMIT = LIMIT - 20;

        RetrofitClient.getInstance()
                .api()
                .getProducts(categoryId, params.key, LIMIT)
                .enqueue(new Callback<BestSellerResult>() {
                    @Override
                    public void onResponse(Call<BestSellerResult> call, Response<BestSellerResult> response) {
                        if (response.body() != null) {

                            Integer key = response.body().getResults().isEmpty() ? null : params.key - 20;
                            callback.onResult(response.body().getResults(), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<BestSellerResult> call, Throwable t) {
                        String message = t.getMessage();
                        Log.d("failure", message);
                    }
                });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Product> callback) {

        LIMIT = LIMIT + 20;

        RetrofitClient.getInstance()
                .api()
                .getProducts(categoryId, params.key, LIMIT)
                .enqueue(new Callback<BestSellerResult>() {
                    @Override
                    public void onResponse(Call<BestSellerResult> call, Response<BestSellerResult> response) {
                        if (response.body() != null) {

                            Integer key = response.body().getResults().isEmpty() ? null : params.key + 20;
                            callback.onResult(response.body().getResults(), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<BestSellerResult> call, Throwable t) {
                        String message = t.getMessage();
                        Log.d("failure", message);
                    }
                });
    }
}