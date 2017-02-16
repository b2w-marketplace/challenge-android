package br.com.lodjinha.alodjinha.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.zip.Inflater;

import br.com.lodjinha.alodjinha.HomeActivity;
import br.com.lodjinha.alodjinha.R;

/**
 * Created by douglasromano on 13/02/2017.
 */

public class ViewPagerAdapter extends PagerAdapter {

    ArrayList<Bitmap> image;
    LayoutInflater inflater;
    Context context;


    public ViewPagerAdapter(HomeActivity activity, ArrayList<Bitmap> img){
        this.context = activity;
        this.image = img;
    }


    @Override
    public int getCount() {
        return image.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==((RelativeLayout)object);
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView bannerimg;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemview = inflater.inflate(R.layout.item_viewpager, container, false);
        bannerimg = (ImageView) itemview.findViewById(R.id.bannerimg);
        bannerimg.setImageBitmap(image.get(position));


        ((ViewPager)container).addView(itemview);
        return itemview;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager)container).removeView((RelativeLayout)object);
    }
}
