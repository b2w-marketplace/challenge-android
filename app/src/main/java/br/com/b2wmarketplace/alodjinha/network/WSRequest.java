package br.com.b2wmarketplace.alodjinha.network;

import okhttp3.FormBody;
import okhttp3.RequestBody;

class WSRequest {

    static RequestBody reservarProdutoBody() {
        return new FormBody.Builder()
                .build();
    }
}
