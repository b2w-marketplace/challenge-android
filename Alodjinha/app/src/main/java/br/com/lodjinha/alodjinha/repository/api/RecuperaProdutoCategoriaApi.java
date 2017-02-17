package br.com.lodjinha.alodjinha.repository.api;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import br.com.lodjinha.alodjinha.interfaceapi.IRecuperaMaisVendidos;
import br.com.lodjinha.alodjinha.interfaceapi.IRecuperaProdutoCategoria;
import br.com.lodjinha.alodjinha.util.UtilJson;

/**
 * Created by douglasromano on 14/02/2017.
 */

public class RecuperaProdutoCategoriaApi extends AsyncTask<String, Void, String> {
    private String json = "";
    private Activity activity;
    private int id;
    public IRecuperaProdutoCategoria delegate = null;
    private static int limite;

    public RecuperaProdutoCategoriaApi(Activity activity, int id, int limite){
        this.activity = activity;
        this.id = id;
        this.limite = limite;
    }

    @Override
    protected String doInBackground(String... strings) {
        URL url = null;
        try {
            int limiteProd = 20+limite;
            url = new URL("https://alodjinha.herokuapp.com/produto?limit="+limiteProd+"&categoriaId="+id);
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
            delegate.preencheProdutoCategoria(json);
        } catch (Exception ex){
            Log.i("ERRO RETORNO API: ", "ERRO RECUPERAIMGBANNER: "+ex.getMessage());
            throw ex;
        }
    }
}
