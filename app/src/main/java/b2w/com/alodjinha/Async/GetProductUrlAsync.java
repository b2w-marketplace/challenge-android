package b2w.com.alodjinha.Async;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import b2w.com.alodjinha.Interface.ProductAsyncResponse;
import b2w.com.alodjinha.Model.Category;
import b2w.com.alodjinha.Model.Product;

public class GetProductUrlAsync extends AsyncTask<String, Void, String> {

    private Context context;

    public ProductAsyncResponse delegate = null;

    public GetProductUrlAsync(Context context) {
        this.context = context;
    }


    @Override
    protected String doInBackground(String... params) {

        String surl = params[0];

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(surl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String linha;
            StringBuffer buffer = new StringBuffer();
            while((linha = reader.readLine()) != null) {
                buffer.append(linha);
                buffer.append("\n");
            }

            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            if (urlConnection != null) {
                urlConnection.disconnect();
            }

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(String data) {

        List<Product> products = new ArrayList<>();

        try{

            JSONObject jObjRoot = new JSONObject(data);
            JSONArray jArray = jObjRoot.getJSONArray("data");
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jObj = jArray.getJSONObject(i);

                Product product = new Product();
                product.setId(jObj.getString("id"));
                product.setName(jObj.getString("nome"));
                product.setUrlImage(jObj.getString("urlImagem"));
                product.setDescription(jObj.getString("descricao"));
                product.setOriginalPrice(jObj.getString("precoDe"));
                product.setNewPrice(jObj.getString("precoPor"));

                JSONObject jObjCategory = jObj.getJSONObject("categoria");

                Category category = new Category();
                category.setId(jObjCategory.getString("id"));
                category.setUrlImage(jObjCategory.getString("urlImagem"));
                category.setDescription(jObjCategory.getString("descricao"));

                product.setCategory(category);


                products.add(product);
            }



        } catch (Exception e){
            e.printStackTrace();
        }

        delegate.processFinishProduct(products);

    }


    @Override
    protected void onPreExecute() {

    }


}