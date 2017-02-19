package br.com.kakobotasso.lodjinha.utils;

import android.app.Activity;
import android.content.Intent;

import br.com.kakobotasso.lodjinha.MainActivity;
import br.com.kakobotasso.lodjinha.SobreActivity;

public class Navegacao {
    public static void vaiParaHome(Activity activity){
        Intent home = new Intent(activity, MainActivity.class);
        activity.startActivity(home);
    }

    public static void vaiParaSobre(Activity activity){
        Intent sobre = new Intent(activity, SobreActivity.class);
        activity.startActivity(sobre);
    }
}
