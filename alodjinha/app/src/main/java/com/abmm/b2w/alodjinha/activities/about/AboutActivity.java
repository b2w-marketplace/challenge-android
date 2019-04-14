package com.abmm.b2w.alodjinha.activities.about;

import android.widget.TextView;

import com.abmm.b2w.alodjinha.R;
import com.abmm.b2w.alodjinha.activities.BaseNavDrawerActivity;

import butterknife.BindView;

public class AboutActivity extends BaseNavDrawerActivity {

    @BindView(R.id.about_dev_name_txt) TextView devName;

    @Override
    protected int getLayout() {
        return R.layout.activity_about;
    }

    @Override
    public void setupTopbar() {
        // default settings
    }

}
