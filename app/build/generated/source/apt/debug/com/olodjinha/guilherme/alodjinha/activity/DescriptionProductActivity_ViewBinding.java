// Generated code from Butter Knife. Do not modify!
package com.olodjinha.guilherme.alodjinha.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.olodjinha.guilherme.alodjinha.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DescriptionProductActivity_ViewBinding implements Unbinder {
  private DescriptionProductActivity target;

  private View view2131361871;

  @UiThread
  public DescriptionProductActivity_ViewBinding(DescriptionProductActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DescriptionProductActivity_ViewBinding(final DescriptionProductActivity target,
      View source) {
    this.target = target;

    View view;
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.imageViewProduct = Utils.findRequiredViewAsType(source, R.id.imageViewProduct, "field 'imageViewProduct'", ImageView.class);
    target.textViewProduct = Utils.findRequiredViewAsType(source, R.id.textViewProduct, "field 'textViewProduct'", TextView.class);
    target.textViewPriceOld = Utils.findRequiredViewAsType(source, R.id.textViewPriceOld, "field 'textViewPriceOld'", TextView.class);
    target.textViewPriceNew = Utils.findRequiredViewAsType(source, R.id.textViewPriceNew, "field 'textViewPriceNew'", TextView.class);
    target.textViewDescription = Utils.findRequiredViewAsType(source, R.id.textViewDescription, "field 'textViewDescription'", TextView.class);
    view = Utils.findRequiredView(source, R.id.floatingActionButtonBookProduct, "field 'floatingActionButtonBookProduct' and method 'bookProduct'");
    target.floatingActionButtonBookProduct = Utils.castView(view, R.id.floatingActionButtonBookProduct, "field 'floatingActionButtonBookProduct'", FloatingActionButton.class);
    view2131361871 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.bookProduct();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    DescriptionProductActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.imageViewProduct = null;
    target.textViewProduct = null;
    target.textViewPriceOld = null;
    target.textViewPriceNew = null;
    target.textViewDescription = null;
    target.floatingActionButtonBookProduct = null;

    view2131361871.setOnClickListener(null);
    view2131361871 = null;
  }
}
