// Generated code from Butter Knife. Do not modify!
package com.olodjinha.guilherme.alodjinha.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.olodjinha.guilherme.alodjinha.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ListProductByCategoryActivity_ViewBinding implements Unbinder {
  private ListProductByCategoryActivity target;

  @UiThread
  public ListProductByCategoryActivity_ViewBinding(ListProductByCategoryActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ListProductByCategoryActivity_ViewBinding(ListProductByCategoryActivity target,
      View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.recyclerViewProducts = Utils.findRequiredViewAsType(source, R.id.recyclerViewProducts, "field 'recyclerViewProducts'", RecyclerView.class);
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.progressBar, "field 'progressBar'", ProgressBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ListProductByCategoryActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.recyclerViewProducts = null;
    target.progressBar = null;
  }
}
