package br.com.b2wmarketplace.alodjinha.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;

import br.com.b2wmarketplace.alodjinha.R;

public class BannerViewHolder extends RecyclerView.ViewHolder {
    private SimpleDraweeView imgFotoBanner;

    public BannerViewHolder(View view) {
        super(view);
        imgFotoBanner = view.findViewById(R.id.imgFotoBanner);
    }

    public void setFotoBanner(String urlimagem) {
        imgFotoBanner.setImageURI(urlimagem.replace("http://", "https://"));
    }
}
