package br.com.wcisang.alojinha.util;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by WCisang on 07/06/2018.
 */
public class Binder {

    @BindingAdapter({"app:urlImagem"})
    public static void loadImage(ImageView view, String url) {
        if (url == null || url.isEmpty())
            url = "http://www.physiotherapyns.ca/images/default_news.png";
        Picasso.with(view.getContext()).load(url).fit().into(view);
    }
}
