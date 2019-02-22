package br.com.b2wmarketplace.alodjinha.util;

import android.graphics.Paint;
import android.widget.TextView;

import java.util.Locale;

public class Util {

    public static String formatarDecimal(double valor){
        return String.format(Locale.getDefault(), "%.2f", valor);
    }

    public static void riscaTextView(TextView txtView){
        txtView.setPaintFlags(txtView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }
}
