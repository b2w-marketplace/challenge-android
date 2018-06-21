package com.olodjinha.guilherme.alodjinha.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.olodjinha.guilherme.alodjinha.R;
import com.olodjinha.guilherme.alodjinha.model.Banner;

/**
 * Created by Guilherme on 19/06/2018.
 */

public class BannerFragment extends Fragment {

    private LinearLayout linearLayoutBanners;
    private ImageView imageViewBanner;
    private Banner detail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_banner, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        linearLayoutBanners = view.findViewById(R.id.linearLayoutBanners);
        imageViewBanner = view.findViewById(R.id.imageViewBanner);

        Glide.with(getContext()).load(getDetail().getUrlImagem()).into(imageViewBanner);

        linearLayoutBanners.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.submarino.com.br"));
                startActivity(browserIntent);
            }
        });
    }

    public Banner getDetail() {
        return detail;
    }

    public void setDetail(Banner detail) {
        this.detail = detail;
    }
}