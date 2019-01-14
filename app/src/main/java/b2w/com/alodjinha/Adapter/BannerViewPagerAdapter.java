package b2w.com.alodjinha.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import b2w.com.alodjinha.Model.Banner;
import b2w.com.alodjinha.R;

public class BannerViewPagerAdapter extends PagerAdapter {

    private Context context;


    private List<Banner> listBanner;

    public BannerViewPagerAdapter(Context context, List<Banner> listBanner) {

        this.context = context;
        this.listBanner = listBanner;
    }

    @Override
    public int getCount() {
        return listBanner.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.layout_banner, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.banner_image_view);

        Picasso.with(context)
                .load(listBanner.get(position).getUrlImage())
                .into(imageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((position % 3) == 0){
                    goToUrl ( context.getString(R.string.submarino_url) );

                } else if((position % 3) == 1){
                    goToUrl ( context.getString(R.string.americanas_url) );

                } else {
                    goToUrl ( context.getString(R.string.shoptime_url) );

                }

            }
        });


        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }

    private void goToUrl (String url) {
        try {
            Uri uriUrl = Uri.parse(url);
            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
            context.startActivity(launchBrowser);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }
}
