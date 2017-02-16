package br.com.lodjinha.alodjinha.repository.api;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import br.com.lodjinha.alodjinha.interfaceapi.IRecuperaImgBanner;
import br.com.lodjinha.alodjinha.util.UtilJson;

/**
 * Created by Desenvolvimento on 28/09/2016.
 */
public class RecuperaImgBannerApi extends AsyncTask<String, Void, String> {

    private String json = "";
    private Activity activity;
    public IRecuperaImgBanner delegate = null;

    public RecuperaImgBannerApi(Activity activity){
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... strings) {
        URL url = null;
        try {
            url = new URL("https://alodjinha.herokuapp.com/banner");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == 200) {
                InputStream conteudo = conn.getInputStream();
                json = UtilJson.toString(conteudo);
            }

            if(conn.getResponseCode() == 404){
                InputStream conteudo = conn.getErrorStream();
                json = UtilJson.toString(conteudo);
            }

        }catch (Exception e){
            e.getMessage();
        }
        return json;
    }

    @Override
    protected void onPostExecute(String json) {
       try {
           delegate.preencheBanner(json);
       } catch (Exception ex){
           Log.i("ERRO RETORNO API: ", "ERRO RECUPERAIMGBANNER: "+ex.getMessage());
           throw ex;
       }
    }
}
