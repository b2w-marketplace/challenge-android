package com.abmm.b2w.alodjinha.activities.product_detailed;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;

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

        FloatingActionButton fab = findViewById(R.id.product_details_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
