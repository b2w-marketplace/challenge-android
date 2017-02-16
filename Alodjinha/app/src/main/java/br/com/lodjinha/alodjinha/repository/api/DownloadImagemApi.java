package br.com.lodjinha.alodjinha.repository.api;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import br.com.lodjinha.alodjinha.interfaceapi.IRecuperaImagemDownload;
import br.com.lodjinha.alodjinha.model.Imagem;

/**
 * Created by douglasromano on 15/02/2017.
 */

public class DownloadImagemApi extends AsyncTask<String, Void, Imagem> {

    ArrayList<String> urlImage = null;

    public IRecuperaImagemDownload delegate = null;
    Imagem img = null;

    public DownloadImagemApi(ArrayList<String> urlImage, Imagem img){
        this.urlImage = urlImage;
        this.img = img;
    }

    @Override
    protected Imagem doInBackground(String... strings) {
        return download_Image(urlImage);
    }

    @Override
    protected void onPostExecute(Imagem result) {
        delegate.preencheImagenss(result);
    }

    private Imagem download_Image(ArrayList<String> url) {
        ArrayList<Bitmap> bmps = new ArrayList<>();
        Bitmap bmp = null;
        Imagem imgs = new Imagem();
        imgs.setTipo(img.getTipo());
        for (String u: url) {
            try{

                URL ulrn = new URL(u);
                HttpURLConnection con = (HttpURLConnection)ulrn.openConnection();
                con.setConnectTimeout(1000);
                InputStream is = con.getInputStream();
                bmp = BitmapFactory.decodeStream(is);
                bmps.add(bmp);
                imgs.setBmps(bmps);
            } catch(Exception e){
                return imgs;
            }
        }
        if (null != bmp) {
            return imgs;
        } else {
            return imgs;
        }
    }
}
