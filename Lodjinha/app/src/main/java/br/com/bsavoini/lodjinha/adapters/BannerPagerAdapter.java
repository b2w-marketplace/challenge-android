package br.com.bsavoini.lodjinha.adapters;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import br.com.bsavoini.lodjinha.R;
import br.com.bsavoini.lodjinha.api.model.BannerModel;
import br.com.bsavoini.lodjinha.callbacks.BannerClickCallback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BannerPagerAdapter extends PagerAdapter {
    private List<BannerModel> banners;
    private BannerClickCallback bannerClickCallback;

    public BannerPagerAdapter(List<BannerModel> banners, BannerClickCallback bannerClickCallback) {
        this.banners = banners;
        this.bannerClickCallback = bannerClickCallback;
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

        final BannerModel bannerModel = banners.get(position);
        Picasso.with(container.getContext())
                .load(bannerModel.getImageURL())
                .placeholder(R.drawable.menu_pattern)
                .into(imgBanner);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bannerClickCallback.onClickBanner(bannerModel.getLinkURL());
            }
        });

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}

