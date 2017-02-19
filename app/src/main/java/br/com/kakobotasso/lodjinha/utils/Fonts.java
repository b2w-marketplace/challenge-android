package br.com.kakobotasso.lodjinha.utils;

import android.app.Activity;
import android.graphics.Typeface;
import android.widget.TextView;

public class Fonts {
    public static void aplicaEstilo(Activity activity, TextView textView){
        Typeface font = Typeface.createFromAsset(activity.getAssets(), "Pacifico_Regular.ttf");
        textView.setTypeface(font);
    }
}
