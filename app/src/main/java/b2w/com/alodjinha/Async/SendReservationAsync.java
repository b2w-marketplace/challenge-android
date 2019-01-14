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
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import b2w.com.alodjinha.Interface.StringAsyncResponse;
import b2w.com.alodjinha.R;
import b2w.com.alodjinha.ShowProduct;


public class SendReservationAsync extends AsyncTask<String, Void, String> {

    private Context context;
    private ProgressDialog pd = null;

    public StringAsyncResponse delegate = null;



    public SendReservationAsync(Context context) {
        this.context = context;
    }





    @Override
    protected void onPreExecute() {

        pd = ProgressDialog.show(context, context.getString(R.string.loading_text), context.getString(R.string.please_wait_text));
    }

    @Override
    protected String doInBackground(String... params) {

        String surl = params[0];

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(surl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
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

        super.onPostExecute(data);

        String result = "";

        try{

            JSONObject jObj = new JSONObject(data);
            result = jObj.getString("result");

        } catch (Exception e){
            e.printStackTrace();
        }


        try {

            delegate.processFinishString(result);
            if (pd != null && pd.isShowing()) {
                pd.dismiss();
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }


}
