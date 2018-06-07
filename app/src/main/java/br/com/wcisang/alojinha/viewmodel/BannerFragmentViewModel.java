package br.com.wcisang.alojinha.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;
import java.util.concurrent.TimeUnit;

import br.com.wcisang.alojinha.model.Banner;
import br.com.wcisang.alojinha.service.BannerService;
import br.com.wcisang.alojinha.service.response.BannerResponse;
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
public class BannerFragmentViewModel extends ViewModel {

    private MutableLiveData<List<Banner>> listMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<List<Banner>> getListMutableLiveData(){
        return listMutableLiveData;
    }

    public void init() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LocalConstants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build();

        BannerService service = retrofit.create(BannerService.class);
        startService(service);
    }

    private void startService(BannerService service) {
        service.getBannerList().enqueue(new Callback<BannerResponse>() {
            @Override
            public void onResponse(Call<BannerResponse> call, Response<BannerResponse> response) {
                listMutableLiveData.setValue(response.body().getData());
            }

            @Override
            public void onFailure(Call<BannerResponse> call, Throwable t) {
                listMutableLiveData.setValue(null);
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
