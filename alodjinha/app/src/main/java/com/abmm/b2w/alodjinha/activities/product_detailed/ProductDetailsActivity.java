package com.abmm.b2w.alodjinha.activities.product_detailed;

import com.abmm.b2w.alodjinha.R;
import com.abmm.b2w.alodjinha.activities.BaseAppCompatActivity;
import com.abmm.b2w.alodjinha.model.Product;
import com.abmm.b2w.alodjinha.utils.Constants;

import org.parceler.Parcels;

public class ProductDetailsActivity extends BaseAppCompatActivity {

    private Product mProduct;

    @Override
    protected int getLayout() {
        return R.layout.activity_product;
    }

    @Override
    protected void initUi() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mProduct = Parcels.unwrap(getIntent().getParcelableExtra(Constants.Keys.PRODUCT_DATA));

        setTitle(mProduct.getName());
    }
}
