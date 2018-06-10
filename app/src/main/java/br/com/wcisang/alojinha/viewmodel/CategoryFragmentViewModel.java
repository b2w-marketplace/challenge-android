package br.com.wcisang.alojinha.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;
import java.util.concurrent.TimeUnit;

import br.com.wcisang.alojinha.model.Category;
import br.com.wcisang.alojinha.service.CategoryService;
import br.com.wcisang.alojinha.service.response.CategoryResponse;
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
public class CategoryFragmentViewModel extends ViewModel {

    private MutableLiveData<List<Category>> listMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<List<Category>> getListMutableLiveData(){
        return listMutableLiveData;
    }

    public void init() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LocalConstants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build();

        CategoryService service = retrofit.create(CategoryService.class);
        startService(service);
    }

    private void startService(CategoryService service) {
        service.getCategoryList().enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                listMutableLiveData.setValue(response.body().getData());
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                listMutableLiveData.setValue(null);
                startService(service);
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
