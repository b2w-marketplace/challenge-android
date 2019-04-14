package com.abmm.b2w.alodjinha.activities.main;

import android.app.Activity;

import com.abmm.b2w.alodjinha.R;
import com.abmm.b2w.alodjinha.activities.BaseNavDrawerActivity;

public class MainActivity extends BaseNavDrawerActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void setupTopbar() {
        toolbar.setTitleTextAppearance(this, R.style.LodjinhaTheme_Toolbar);
        toolbar.setLogo(R.drawable.logo_navbar);
    }

}