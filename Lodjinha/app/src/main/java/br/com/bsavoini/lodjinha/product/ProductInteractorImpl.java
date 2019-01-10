package br.com.bsavoini.lodjinha.product;

import br.com.bsavoini.lodjinha.api.RetrofitInstance;
import br.com.bsavoini.lodjinha.api.model.ReservationResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductInteractorImpl implements ProductContract.ProductInteractor {

    @Override
    public void requestProductReservation(int productId, final ProductReservationRequestListener listener) {
        Call<ReservationResponse> productsResponseCall = RetrofitInstance.getLodjinhaService().requestProductReservation(productId);

        productsResponseCall.enqueue(new Callback<ReservationResponse>() {
            @Override
            public void onResponse(Call<ReservationResponse> call, Response<ReservationResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listener.onProductReservationRequestSuccessful(response.body());
                } else {
                    listener.onProductReservationFail();
                }
            }

            @Override
            public void onFailure(Call<ReservationResponse> call, Throwable t) {
                listener.onProductReservationFail();
            }
        });
    }
}

