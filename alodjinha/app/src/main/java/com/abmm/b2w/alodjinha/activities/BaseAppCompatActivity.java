package com.abmm.b2w.alodjinha.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.abmm.b2w.alodjinha.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseAppCompatActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) protected Toolbar toolbar;
    @BindView(R.id.root_layout) protected ViewGroup mRoot;

    private ProgressBarVH holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        ButterKnife.bind(this);
        holder = new ProgressBarVH(mRoot);
        initUi();
    }

    @Override
    protected void onResume() {
        super.onResume();
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

    /* Errors */
    public void showError() {
        Snackbar.make(mRoot, R.string.connectivity_error, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.try_again, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        makeRequests();
                    }
                })
                .setActionTextColor(Color.RED)
                .show();
        releaseUi();
    }

    public void showError(int code) {

    }

    /* Loading */
    protected void blockUi() {
        holder.showLoading();
    }

    public void releaseUi() {
        holder.hideLoading();
    }

    class ProgressBarVH {
        @Nullable @BindView(R.id.progress_bar_layout) View pbarLayout;

        ProgressBarVH(View view) {
            ButterKnife.bind(this, view);
        }

        void showLoading() {
            if (mRoot != null) {
                boolean hasLoading = pbarLayout == null;
                if (hasLoading) {
                    View loadingView = View.inflate(getApplicationContext(), R.layout.progress_bar, null);
                    loadingView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
                    mRoot.addView(loadingView, 0);
                    loadingView.bringToFront();
                }
            }
        }

        void hideLoading() {
            if (mRoot != null) {
                View existingLoading = mRoot.findViewById(R.id.progress_bar_layout);
                if (existingLoading != null) {
                    mRoot.removeView(existingLoading);
                }
            }
        }
    }
}
