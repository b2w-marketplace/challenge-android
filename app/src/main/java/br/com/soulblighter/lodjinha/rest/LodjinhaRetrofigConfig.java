package br.com.soulblighter.lodjinha.rest;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import br.com.soulblighter.lodjinha.BuildConfig;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class LodjinhaRetrofigConfig {

    public static final String BASE_URL = "https://alodjinha.herokuapp.com/";

    private final Retrofit mRetrofit;
    private final Gson mGson;
    private final OkHttpClient.Builder mOkHttpClient;
    private static Cache cache = null;

    public LodjinhaRetrofigConfig(Context context) {

        if (cache == null) {
            int cacheSize = 10 * 1024 * 1024; // 10 MB
            cache = new Cache(context.getCacheDir(), cacheSize);
        }

        mOkHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            mOkHttpClient.addInterceptor(logging);
        }

        mGson = new GsonBuilder()
                .setLenient()
                .create();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(mOkHttpClient.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .build();
    }

    public LodjinhaService getLodjinhaService() {
        return mRetrofit.create(LodjinhaService.class);
    }

}
