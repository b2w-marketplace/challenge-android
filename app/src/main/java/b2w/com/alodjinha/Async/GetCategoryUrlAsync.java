package b2w.com.alodjinha.Async;

import android.app.ProgressDialog;
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

import b2w.com.alodjinha.Interface.CategoryAsyncResponse;
import b2w.com.alodjinha.Model.Category;
import b2w.com.alodjinha.R;

public class GetCategoryUrlAsync extends AsyncTask<String, Void, String> {

    private Context context;

    private ProgressDialog pd;
    public CategoryAsyncResponse delegate = null;

    public GetCategoryUrlAsync(Context context) {
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

        List<Category> categories = new ArrayList<>();

        try{

            JSONObject jObjRoot = new JSONObject(data);
            JSONArray jArray = jObjRoot.getJSONArray("data");
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jObj = jArray.getJSONObject(i);

                Category category = new Category();
                category.setId(jObj.getString("id"));
                category.setUrlImage(jObj.getString("urlImagem"));
                category.setDescription(jObj.getString("descricao"));

                categories.add(category);
            }



        } catch (Exception e){
            e.printStackTrace();
        }

        try {

            delegate.processFinishCategory(categories);
            if (pd != null && pd.isShowing()) {
                pd.dismiss();
            }
        } catch (Exception e){
            e.printStackTrace();
        }


    }


    @Override
    protected void onPreExecute() {
        pd = ProgressDialog.show(context, context.getString(R.string.loading_text), context.getString(R.string.please_wait_text));
    }


}