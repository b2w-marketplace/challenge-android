package br.com.lodjinha.alodjinha.repository.api;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import br.com.lodjinha.alodjinha.util.UtilJson;

/**
 * Created by douglasromano on 17/02/2017.
 */

public class ReservaProdutoApi  extends AsyncTask<String, Void, String>{

    private Activity activity;
    private String json = "";
    private int id;
    private AlertDialog alerta;

    public ReservaProdutoApi(Activity activity, int id){
        this.activity = activity;
        this.id = id;
    }


    @Override
    protected String doInBackground(String... params) {
        try{
            URL url = new URL("https://alodjinha.herokuapp.com/produto/"+id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("charset", "utf-8");
            conn.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());


            JSONObject param = new JSONObject();
            param.put("produtoId", id);

            out.writeBytes(param.toString());

            out.flush();
            out.close();


            InputStream conteudo = conn.getInputStream();
            json = UtilJson.toString(conteudo);

        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return json;
    }

    @Override
    protected void onPostExecute(String json) {

        try {
            JSONObject result = new JSONObject(json);
            final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle("");
            builder.setMessage("Produto reservado com sucesso");
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    alerta.dismiss();
                }
            });
            alerta = builder.create();
            alerta.show();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
