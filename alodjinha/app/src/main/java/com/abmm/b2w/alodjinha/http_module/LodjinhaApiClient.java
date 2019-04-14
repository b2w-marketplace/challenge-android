package com.abmm.b2w.alodjinha.http_module;

import com.abmm.b2w.alodjinha.utils.Constants.LodjinhaServer;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LodjinhaApiClient {

    private static ILodjinhaApi apiClient = null;

    public static ILodjinhaApi buildApiClient() {
        if (apiClient == null) {
            apiClient = createApiClient();
        }
        return apiClient;
    }

    private static ILodjinhaApi createApiClient() {
        final OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .addInterceptor(new PublicApiInterceptor());

        final OkHttpClient client = clientBuilder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LodjinhaServer.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(client)
                .build();

        return retrofit.create(ILodjinhaApi.class);
    }
}
