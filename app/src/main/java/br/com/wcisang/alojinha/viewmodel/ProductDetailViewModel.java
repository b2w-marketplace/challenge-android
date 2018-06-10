package br.com.wcisang.alojinha.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.concurrent.TimeUnit;

import br.com.wcisang.alojinha.service.ProductService;
import br.com.wcisang.alojinha.service.response.ReservationResponse;
import br.com.wcisang.alojinha.util.LocalConstants;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by WCisang on 07/06/2018.
 */
public class ProductDetailViewModel extends ViewModel {

    MutableLiveData<ReservationResponse> mutableLiveData = new MutableLiveData<>();

    public MutableLiveData<ReservationResponse> getMutableLiveData() {
        return mutableLiveData;
    }

    public void sendReservation(String id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LocalConstants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build();

        ProductService service = retrofit.create(ProductService.class);
        service.postReservation(id).enqueue(new Callback<ReservationResponse>() {
            @Override
            public void onResponse(Call<ReservationResponse> call, Response<ReservationResponse> response) {
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ReservationResponse> call, Throwable t) {
                mutableLiveData.setValue(null);
            }
        });
    }

    private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(3, TimeUnit.SECONDS);
        okHttpClient.readTimeout(3, TimeUnit.SECONDS);
        okHttpClient.retryOnConnectionFailure(true);
        return okHttpClient.build();
    }
}
