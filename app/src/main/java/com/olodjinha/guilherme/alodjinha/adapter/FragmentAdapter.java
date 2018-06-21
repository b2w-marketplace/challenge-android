package com.olodjinha.guilherme.alodjinha.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.olodjinha.guilherme.alodjinha.fragment.BannerFragment;
import com.olodjinha.guilherme.alodjinha.model.Banner;

import java.util.List;

/**
 * Created by Guilherme on 19/06/2018.
 */

public class FragmentAdapter extends FragmentStatePagerAdapter {
    private Context ctx;
    private List<Banner> data;
    private Fragment[] fragments;

    public FragmentAdapter(Context ctx, FragmentManager fm, List<Banner> data) {
        super(fm);
        this.ctx = ctx;
        this.data = data;
        fragments = new Fragment[data.size()];
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        Banner items = data.get(position);

        BannerFragment bannerFragment = new BannerFragment();
        bannerFragment.setDetail(items);
        fragment = bannerFragment;

        if (fragments[position] == null) {
            fragments[position] = fragment;
        }
        return fragments[position];
    }

    @Override
    public int getCount() {
        if (data != null) {
            return data.size();
        } else {
            return 0;
        }
    }
}