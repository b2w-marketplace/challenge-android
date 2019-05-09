package br.com.douglas.fukuhara.lodjinha.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;

import br.com.douglas.fukuhara.lodjinha.R;
import br.com.douglas.fukuhara.lodjinha.network.vo.ProductDataVo;

public class ProductDetailActivity extends AppCompatActivity {

    private static final String PRODUCT_BUNDLE_KEY = "product_bundle";

    public static Intent newIntent(Context context, ProductDataVo productDataVo) {
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra(PRODUCT_BUNDLE_KEY, new Gson().toJson(productDataVo));
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        ProductDataVo productDataVo;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey(PRODUCT_BUNDLE_KEY)) {
            productDataVo = new Gson().fromJson(bundle.getString(PRODUCT_BUNDLE_KEY), ProductDataVo.class);
            getSupportActionBar().setTitle(productDataVo.getCategoria().getDescricao());
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
