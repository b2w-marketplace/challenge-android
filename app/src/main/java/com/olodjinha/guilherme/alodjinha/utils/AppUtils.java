package com.olodjinha.guilherme.alodjinha.utils;

import android.content.Context;

/**
 * Created by Guilherme on 20/06/2018.
 */

public class AppUtils {

    public static String formatToMoney(Context context, float value) {
        String decimals = "" + (int) Math.abs(Math.round(value % 1 * 100));
        while (decimals.length() < 2)
            decimals = "0" + decimals;

        String integers = "" + (int) (value < 0 ? value * -1 : value);
        String compose = "";

        for (int i = integers.length() - 1; i >= 0; i--)
            compose = integers.charAt(i) + (compose.length() > 0 && compose.replace(".", "").length() % 3 == 0 ? "." : "") + compose;

        return (value < 0 ? "-" : "") + compose + "," + decimals;
    }
}
