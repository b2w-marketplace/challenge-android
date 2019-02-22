package br.com.b2wmarketplace.alodjinha.common;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import br.com.b2wmarketplace.alodjinha.R;

public class ErroWs {

    public static void retornaErro(Context context, ProgressBar pbProgresso){
        Toast.makeText(context, R.string.erro_conexao, Toast.LENGTH_SHORT).show();
        pbProgresso.setVisibility(View.GONE);
    }
}
