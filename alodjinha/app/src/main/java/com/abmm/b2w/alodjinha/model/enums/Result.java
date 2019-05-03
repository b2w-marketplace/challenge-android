package com.abmm.b2w.alodjinha.model.enums;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;

public class Result {
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";

    @Retention(SOURCE)
    @StringDef({SUCCESS, ERROR})
    public @interface ResultConstantInterface { }
}
