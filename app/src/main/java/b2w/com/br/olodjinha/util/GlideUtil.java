package b2w.com.br.olodjinha.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.request.RequestOptions;

import b2w.com.br.olodjinha.R;

public class GlideUtil {

    public static void setBannerImage(Context mContext, ImageView mImageView, String url) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions
                .error(R.drawable.background_placeholder_image)
                .placeholder(R.drawable.background_placeholder_image);


        GlideApp.with(mContext)
                .load(url)
                .apply(requestOptions)
                .into(mImageView);
    }
}
