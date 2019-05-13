package br.com.douglas.fukuhara.lodjinha.network;

public interface RestClient {

    RestApi getApi();

    void setBaseUrl(String baseUrl);
}
