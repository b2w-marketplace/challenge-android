// Generated code from Butter Knife. Do not modify!
package com.olodjinha.guilherme.alodjinha.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.olodjinha.guilherme.alodjinha.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  private View view2131361924;

  private View view2131361922;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(final MainActivity target, View source) {
    this.target = target;

    View view;
    target.recyclerViewCategory = Utils.findRequiredViewAsType(source, R.id.recyclerViewCategory, "field 'recyclerViewCategory'", RecyclerView.class);
    target.recyclerViewBestSellers = Utils.findRequiredViewAsType(source, R.id.recyclerViewBestSellers, "field 'recyclerViewBestSellers'", RecyclerView.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.drawerLayout = Utils.findRequiredViewAsType(source, R.id.drawer_layout, "field 'drawerLayout'", DrawerLayout.class);
    target.navigationView = Utils.findRequiredViewAsType(source, R.id.nav_view, "field 'navigationView'", NavigationView.class);
    target.textViewTitleAbout = Utils.findRequiredViewAsType(source, R.id.textViewTitleAbout, "field 'textViewTitleAbout'", TextView.class);
    target.contentMain = Utils.findRequiredView(source, R.id.contentMain, "field 'contentMain'");
    target.relativeLayoutAbout = Utils.findRequiredViewAsType(source, R.id.relativeLayoutAbout, "field 'relativeLayoutAbout'", RelativeLayout.class);
    target.indicator = Utils.findRequiredViewAsType(source, R.id.indicators, "field 'indicator'", LinearLayout.class);
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.viewPager, "field 'viewPager'", ViewPager.class);
    view = Utils.findRequiredView(source, R.id.nav_home_menu, "method 'navHomeMenu'");
    view2131361924 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.navHomeMenu();
      }
    });
    view = Utils.findRequiredView(source, R.id.nav_about_menu, "method 'navAboutMenu'");
    view2131361922 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.navAboutMenu();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerViewCategory = null;
    target.recyclerViewBestSellers = null;
    target.toolbar = null;
    target.drawerLayout = null;
    target.navigationView = null;
    target.textViewTitleAbout = null;
    target.contentMain = null;
    target.relativeLayoutAbout = null;
    target.indicator = null;
    target.viewPager = null;

    view2131361924.setOnClickListener(null);
    view2131361924 = null;
    view2131361922.setOnClickListener(null);
    view2131361922 = null;
  }
}
