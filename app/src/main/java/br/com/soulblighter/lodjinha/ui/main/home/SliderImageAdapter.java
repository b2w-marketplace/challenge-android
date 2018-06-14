package br.com.soulblighter.lodjinha.ui.main.home;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.com.soulblighter.lodjinha.R;
import br.com.soulblighter.lodjinha.databinding.HomeSliderItemBinding;
import br.com.soulblighter.lodjinha.model.Banner;

public class SliderImageAdapter extends PagerAdapter {

    private List<Banner> mBanners = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context mContext;

    public SliderImageAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    public void setBanners(List<Banner> banners) {
        mBanners = banners;
        this.notifyDataSetChanged();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container,
                            int position,
                            @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mBanners == null ? 0 : mBanners.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup parent, final int position) {
        HomeSliderItemBinding binding = DataBindingUtil.inflate
                (mInflater, R.layout.home_slider_item, parent, true);

        Picasso.with(mContext).load(mBanners.get(position).getUrlImagem())
                .placeholder(R.color.colorPrimary).into(binding.image);

        binding.image.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse
                    (mBanners.get(position).getLinkUrl()));
            mContext.startActivity(browserIntent);
        });

        return binding.getRoot();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }
}
