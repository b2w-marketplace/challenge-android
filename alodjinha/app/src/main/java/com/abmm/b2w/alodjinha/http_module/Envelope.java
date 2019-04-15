package com.abmm.b2w.alodjinha.http_module;

import com.google.gson.annotations.Expose;

import java.util.List;

public class Envelope<T> {

    @Expose
    private List<T> data;

    public List<T> getData() { return data; }

}
