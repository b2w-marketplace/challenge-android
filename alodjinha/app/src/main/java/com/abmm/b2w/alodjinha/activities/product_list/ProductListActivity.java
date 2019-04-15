package com.abmm.b2w.alodjinha.activities.product_list;

import android.support.v7.widget.Toolbar;

import com.abmm.b2w.alodjinha.R;
import com.abmm.b2w.alodjinha.activities.BaseAppCompatActivity;
import com.abmm.b2w.alodjinha.model.Category;
import com.abmm.b2w.alodjinha.utils.Constants.Keys;

import org.parceler.Parcels;

import butterknife.BindView;

public class ProductListActivity extends BaseAppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;

    private Category mCategory;

    @Override
    protected void initUi() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.mCategory = Parcels.unwrap(getIntent().getParcelableExtra(Keys.CATEGORY_DATA));

        setTitle(mCategory.getDescription());
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_product_list;
    }

}
