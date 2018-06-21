package com.olodjinha.guilherme.alodjinha.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Guilherme on 20/06/2018.
 */

public class ResultStatus {

    @SerializedName("result")
    String result;

    @SerializedName("mensagem")
    String mensagem;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
