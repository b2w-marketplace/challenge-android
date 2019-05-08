package br.com.douglas.fukuhara.lodjinha.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import br.com.douglas.fukuhara.lodjinha.R;
import br.com.douglas.fukuhara.lodjinha.network.vo.BannerDataVo;

public class HomeBannerViewPagerAdapter extends PagerAdapter {

    private List<BannerDataVo> mBannerItemsList;
    private Context mContext;

    public HomeBannerViewPagerAdapter(List<BannerDataVo> bannerItemsList, Context context) {
        mBannerItemsList = bannerItemsList;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mBannerItemsList == null ? 0 : mBannerItemsList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.banner_item_view_pager, container, false);
        container.addView(layout);

        String urlImagem = mBannerItemsList.get(position).getUrlImagem();
        ImageView imageView = layout.findViewById(R.id.iv_home_banner_image);
        // TODO: Configure: fallback, onError, placeholder
        Glide
                .with(layout)
                .load(urlImagem)
                .into(imageView);

        return layout;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object view) {
        container.removeView((View) view);
    }
}
