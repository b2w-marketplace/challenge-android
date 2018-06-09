package br.com.wcisang.alojinha.util;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;

import br.com.wcisang.alojinha.R;

/**
 * Created by WCisang on 09/06/2018.
 */
public class OnOffsetChanged implements android.support.design.widget.AppBarLayout.OnOffsetChangedListener {

    private Context context;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private String title;
    private boolean isShow = true;
    private int scrollRange = -1;

    public OnOffsetChanged(Context context, CollapsingToolbarLayout collapsingToolbarLayout, String title) {
        this.context = context;
        this.collapsingToolbarLayout = collapsingToolbarLayout;
        this.title = title;
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        Toolbar toolbar = getToolbar(collapsingToolbarLayout);
        if (scrollRange == -1) {
            scrollRange = appBarLayout.getTotalScrollRange();
        }
        if (scrollRange + verticalOffset == 0) {
            collapsingToolbarLayout.setTitle(title);
            toolbar.getNavigationIcon().setColorFilter(ContextCompat
                    .getColor(context, R.color.white_two), PorterDuff.Mode.SRC_ATOP);

            isShow = true;
        } else if (isShow) {
            collapsingToolbarLayout.setTitle(" ");
            collapsingToolbarLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.white_two));
            toolbar.getNavigationIcon().setColorFilter(ContextCompat
                    .getColor(context, R.color.greyish_brown), PorterDuff.Mode.SRC_ATOP);
            isShow = false;
        }
    }

    private Toolbar getToolbar(CollapsingToolbarLayout collapsingToolbarLayout) {
        final int childCount = collapsingToolbarLayout.getChildCount();
        Toolbar toolbar = null;
        for (int i = 0; i < childCount; i++) {
            View v = collapsingToolbarLayout.getChildAt(i);
            if (v instanceof Toolbar) {
                toolbar = (Toolbar) v;
            }
        }
        return toolbar;
    }
}

