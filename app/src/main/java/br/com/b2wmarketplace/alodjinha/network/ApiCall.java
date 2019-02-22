package br.com.b2wmarketplace.alodjinha.network;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

class ApiCall{
    private static OkHttpClient client;
    private static String resposta;
    private static HttpUrl url;

    private static final String strurl = "https://alodjinha.herokuapp.com/";

    static String GET(String finalurl) throws IOException {
        url = HttpUrl.parse(strurl + finalurl);
        assert url != null;
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("cache-control", "no-cache")
                .build();
        client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()){
            resposta = "Erro";
        }else {
            if (response.body() != null) {
                resposta = response.body().string();
            }
        }
        return resposta;
    }

    static String POST(String finalurl, RequestBody body) throws IOException {
        url = HttpUrl.parse(strurl + finalurl);
        assert url != null;
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("cache-control", "no-cache")
                .build();
        client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()){
            resposta = "Erro";
        }else {
            if (response.body() != null) {
                resposta = response.body().string();
            }
        }
        return resposta;
    }
}
