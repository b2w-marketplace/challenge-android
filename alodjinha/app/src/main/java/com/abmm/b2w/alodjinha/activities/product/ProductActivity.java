package com.abmm.b2w.alodjinha.activities.product;

import com.abmm.b2w.alodjinha.R;
import com.abmm.b2w.alodjinha.activities.BaseAppCompatActivity;
import com.abmm.b2w.alodjinha.model.Product;

public class ProductActivity extends BaseAppCompatActivity {

    private Product mProduct;

    @Override
    protected int getLayout() {
        return R.layout.activity_product;
    }

    @Override
    protected void initUi() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        setTitle(mProduct.getName());
    }
}
