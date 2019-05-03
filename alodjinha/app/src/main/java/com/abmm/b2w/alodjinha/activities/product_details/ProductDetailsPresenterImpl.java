package com.abmm.b2w.alodjinha.activities.product_details;

import android.content.DialogInterface;
import android.support.annotation.NonNull;

import com.abmm.b2w.alodjinha.R;
import com.abmm.b2w.alodjinha.http_module.ILodjinhaApi;
import com.abmm.b2w.alodjinha.http_module.LodjinhaApiClient;
import com.abmm.b2w.alodjinha.model.ChartMessage;
import com.abmm.b2w.alodjinha.model.Product;
import com.abmm.b2w.alodjinha.model.enums.Result;
import com.abmm.b2w.alodjinha.activities.IBasePresenterView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsPresenterImpl implements IProductDetailsPresenter {

    private IProductDetailsView mView;

    private Product mProduct;
    private ILodjinhaApi api;

    public ProductDetailsPresenterImpl(IProductDetailsView view) {
        this.mView = view;
        this.api = LodjinhaApiClient.buildApiClient();
    }

    @Override
    public void setProduct(Product product) {
        this.mProduct = product;
    }

    @Override
    public Product getProduct() {
        return this.mProduct;
    }

    @Override
    public void requestProduct() {
        api.getProductById(mProduct.getId()).enqueue(handleProductCallback());
    }

    @Override
    public void reserveProduct() {
        api.addProductToChart(mProduct.getId()).enqueue(handleAddToChartCallback());
    }

    private Callback<Product> handleProductCallback() {
        return new Callback<Product>() {
            @Override
            public void onResponse(@NonNull Call<Product> call, @NonNull Response<Product> response) {
                if (response.isSuccessful()) {
                    mProduct = response.body();
                    mView.showData();
                } else {
                    mView.showError(response.code());
                }
                mView.releaseUi();
            }

            @Override
            public void onFailure(@NonNull Call<Product> call, @NonNull Throwable t) {
                mView.showError();
                mView.releaseUi();
            }
        };
    }

    private Callback<ChartMessage> handleAddToChartCallback() {
        return new Callback<ChartMessage>() {
            @Override
            public void onResponse(@NonNull Call<ChartMessage> call, @NonNull Response<ChartMessage> response) {
                if (response.isSuccessful()) {
                    ChartMessage serverAnswer = response.body();

                    if (Result.SUCCESS.equalsIgnoreCase(serverAnswer.getStatus())) {
                        mView.openAlertDialog(R.string.product_reserved_successfully);
                    } else {
                        mView.openAlertDialog(serverAnswer.getMessage(), null);
                    }
                } else {
                    mView.showError(response.code());
                }
                mView.releaseButton();
            }

            @Override
            public void onFailure(@NonNull Call<ChartMessage> call, @NonNull Throwable t) {
                mView.showError();
                mView.releaseButton();
            }
        };
    }

    interface IProductDetailsView extends IBasePresenterView {

        void showData();

        void openAlertDialog(int resId);

        void openAlertDialog(String message, DialogInterface.OnClickListener listener);

        void releaseButton();
    }
}
