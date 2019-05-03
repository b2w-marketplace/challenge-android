package com.abmm.b2w.alodjinha.model;

import com.abmm.b2w.alodjinha.model.enums.Result.ResultConstantInterface;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChartMessage {

    @ResultConstantInterface
    @Expose
    @SerializedName("result")
    private String status;

    @Expose
    @SerializedName("mensagem")
    private String message;


    /* Getters */
    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }


    /* Setters */
    public void setStatus(@ResultConstantInterface String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
