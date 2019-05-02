package com.abmm.b2w.alodjinha.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.abmm.b2w.alodjinha.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseAppCompatActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        ButterKnife.bind(this);

        initUi();
        makeRequests();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected abstract int getLayout();
    protected abstract void initUi();

    protected void makeRequests() {}
}
