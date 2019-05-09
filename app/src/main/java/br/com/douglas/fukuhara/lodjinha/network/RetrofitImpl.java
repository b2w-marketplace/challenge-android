package br.com.douglas.fukuhara.lodjinha.network;

import br.com.douglas.fukuhara.lodjinha.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitImpl implements RestClient {

    private static RetrofitImpl instance;

    public static RetrofitImpl getInstance() {
        if (instance == null) {
            instance = new RetrofitImpl();
        }
        return instance;
    }

    @Override
    public RestApi getApi() {
        Retrofit.Builder retrofitBuilder =  new Retrofit.Builder()
                .baseUrl(BuildConfig.REST_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor);

            retrofitBuilder.client(client.build());
        }

        Retrofit retrofitClient = retrofitBuilder
                .build();

        return retrofitClient.create(RestApi.class);
    }
}
