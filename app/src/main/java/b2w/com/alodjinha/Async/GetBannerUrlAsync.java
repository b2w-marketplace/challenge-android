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

import b2w.com.alodjinha.Interface.BannerAsyncResponse;
import b2w.com.alodjinha.Model.Banner;
import b2w.com.alodjinha.R;

public class GetBannerUrlAsync extends AsyncTask<String, Void, String> {

    private Context context = null;

    private ProgressDialog pd;
    public BannerAsyncResponse delegate = null;

    public GetBannerUrlAsync(Context context) {
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

        List<Banner> banners = new ArrayList<>();

        try{

            JSONObject jObjRoot = new JSONObject(data);
            JSONArray jArray = jObjRoot.getJSONArray("data");
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jObj = jArray.getJSONObject(i);

                Banner banner = new Banner();
                banner.setId(jObj.getString("id"));
                banner.setUrlImage(jObj.getString("urlImagem"));
                banner.setLinkUrl(jObj.getString("linkUrl"));

                banners.add(banner);
            }



        } catch (Exception e){
            e.printStackTrace();
        }

        try {

            delegate.processFinishBanner(banners);
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

