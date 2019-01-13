package b2w.com.br.olodjinha.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import b2w.com.br.olodjinha.GlideApp;
import b2w.com.br.olodjinha.R;
import b2w.com.br.olodjinha.data.api.RetrofitClient;
import b2w.com.br.olodjinha.data.models.BannerDTO;
import b2w.com.br.olodjinha.util.GlideUtil;

public class BannerAdapter extends PagerAdapter {

    private final List<BannerDTO> mBannerList;
    private final Context mContext;

    public BannerAdapter(Context context, List<BannerDTO> bannerList) {
        mBannerList = bannerList;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mBannerList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == ((ConstraintLayout) o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View itemView = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.banner_item, container, false);

        ImageView mImageView = (ImageView) itemView.findViewById(R.id.imageview_banner);

        GlideUtil.setBannerImage(mContext, mImageView, mBannerList.get(position).getUrl());

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(mBannerList.get(position).getLink()));
                mContext.startActivity(intent);
            }
        });

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ConstraintLayout) object);
    }
}
