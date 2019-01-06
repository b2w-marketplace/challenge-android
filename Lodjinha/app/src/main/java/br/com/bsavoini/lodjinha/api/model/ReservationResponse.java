package br.com.bsavoini.lodjinha.api.model;

import com.google.gson.annotations.SerializedName;

public class ReservationResponse {

    @SerializedName("result")
    private String result;

    @SerializedName("mensagem")
    private String message;

    public String getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }
}
