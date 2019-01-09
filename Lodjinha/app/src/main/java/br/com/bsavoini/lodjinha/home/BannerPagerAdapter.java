package br.com.bsavoini.lodjinha.home;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import br.com.bsavoini.lodjinha.R;
import br.com.bsavoini.lodjinha.api.model.BannerModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BannerPagerAdapter extends PagerAdapter {
    private List<BannerModel> banners;

    public BannerPagerAdapter(List<BannerModel> banners) {
        this.banners = banners;
    }

    @Override
    public int getCount() {
        return banners.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return obj == view;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View view = inflater.inflate(R.layout.item_banner, container, false);
        container.addView(view);

        ImageView imgBanner = view.findViewById(R.id.img_banner);

        Picasso.with(container.getContext())
                .load(banners.get(position).getImageURL())
                .placeholder(R.drawable.menu_pattern)
                .into(imgBanner);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}

