package com.mmart.sidershopping.view.homepage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mmart.sidershopping.model.entity.Banner;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private List<Banner> banners;

    ViewPagerAdapter(Context context, List<Banner> banners) {
        this.context = context;
        this.banners = banners;
    }

    @Override
    public int getCount() {
        return banners.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);

        Banner banner = banners.get(position);
        Picasso.get()
                .load(banner.getUlrImage())
                .fit()
                .centerCrop()
                .into(imageView);
        container.addView(imageView);

        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}