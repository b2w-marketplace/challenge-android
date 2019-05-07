package com.mmart.sidershopping.view.productdetail;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.mmart.sidershopping.model.api.RetrofitClient;
import com.mmart.sidershopping.model.entity.Product;
import com.mmart.sidershopping.model.entity.ProductResult;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class ProductDetailRepository {

    final MutableLiveData product = new MutableLiveData<Product>();


    public MutableLiveData<Product> getProduct(int productId) {

        RetrofitClient.getInstance()
                .api()
                .getProduct(productId)
                .enqueue(new Callback<Product>() {
                    @Override
                    public void onResponse(Call<Product> call, Response<Product> response) {
                        product.postValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<Product> call, Throwable t) {
                        String message = t.getMessage();
                        Log.d("failure", message);
                    }
                });

        return product;
    }

    public MutableLiveData<String> reserve(int productId) {

        final MutableLiveData message = new MutableLiveData<String>();

        RetrofitClient.getInstance()
                .api()
                .reservar(productId)
                .enqueue(new Callback<Product>() {
                    @Override
                    public void onResponse(Call<Product> call, Response<Product> response) {

                        if (response.code() == HttpURLConnection.HTTP_OK) {
                            message.postValue("Produto reservado com sucesso");
                        } else {
                            message.postValue("Produto não pode ser reservado");
                        }
                    }

                    @Override
                    public void onFailure(Call<Product> call, Throwable t) {
                        String message = t.getMessage();
                        Log.d("failure", message);
                    }
                });

        return message;
    }
}
