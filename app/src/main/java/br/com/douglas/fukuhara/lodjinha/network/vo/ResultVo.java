package br.com.douglas.fukuhara.lodjinha.network.vo;

import com.google.gson.annotations.SerializedName;

public class ResultVo {

    @SerializedName("result")
    private String result;

    @SerializedName("mensagem")
    private String mensagem;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
