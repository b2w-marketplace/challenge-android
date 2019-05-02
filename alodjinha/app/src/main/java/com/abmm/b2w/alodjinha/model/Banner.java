package com.abmm.b2w.alodjinha.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Banner {

    @Expose
    private int id;

    @Expose
    @SerializedName("urlImagem")
    private String pictUrl;

    @Expose
    private String linkUrl;

    private int position;
    private boolean isActive;


    /* Getters */
    public int getId() {
        return id;
    }

    public String getPictUrl() {
        return pictUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public int getPosition() { return position; }

    public boolean isActive() { return isActive; }

    /* Setters */
    public void setId(int id) {
        this.id = id;
    }

    public void setPictUrl(String pictUrl) {
        this.pictUrl = pictUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public void setPosition(int position) { this.position = position; }

    public void setActiveON() { isActive = true; }

    public void setActiveOFF() { this.isActive = false; }

    @NonNull
    @Override
    public String toString() {
        return "Banner{" +
                "id=" + this.id +
                ", pictUrl=" + this.pictUrl +
                ", linkUrl=" + this.linkUrl +
                ", isActive=" + this.isActive +
                "";
    }
}
