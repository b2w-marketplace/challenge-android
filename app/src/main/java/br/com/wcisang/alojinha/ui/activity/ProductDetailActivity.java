package br.com.wcisang.alojinha.ui.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

import br.com.wcisang.alojinha.R;
import br.com.wcisang.alojinha.databinding.ActivityProductDetailBinding;
import br.com.wcisang.alojinha.model.Product;
import br.com.wcisang.alojinha.service.response.ReservationResponse;
import br.com.wcisang.alojinha.util.OnOffsetChanged;
import br.com.wcisang.alojinha.viewmodel.ProductDetailViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailActivity extends AppCompatActivity {

    public static final String PRODUCT = "product";
    private ProductDetailViewModel viewModel;
    private Product product;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.appBar)
    AppBarLayout appBar;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityProductDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail);
        ButterKnife.bind(this);
        product = (Product) getIntent().getSerializableExtra(PRODUCT);
        setupToolbar();
        createViewModel();
        binding.setProduct(product);
        binding.content.setProduct(product);
        fab.setOnClickListener((View v) -> reservationClick());
    }

    private void setupToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appBar.addOnOffsetChangedListener(new OnOffsetChanged(this, collapsingToolbarLayout,
                product.getCategoria().getDescricao()));
    }

    private void createViewModel(){
        viewModel = ViewModelProviders.of(this).get(ProductDetailViewModel.class);
        viewModel.getMutableLiveData().observe(this, getReservationObserver());
    }

    private void reservationClick(){
        sendReservation();
    }

    private void sendReservation(){
        animateFab();
        viewModel.sendReservation(String.valueOf(product.getId()));
    }

    private void animateFab(){
        RotateAnimation animation = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(900);
        animation.setRepeatCount(Animation.INFINITE);
        fab.setEnabled(false);
        fab.startAnimation(animation);
    }

    private Observer<ReservationResponse> getReservationObserver() {
        return reservationResponse -> {
            if (reservationResponse == null || !reservationResponse.isSuccess()){
                showFailedMessage();
            }else
                showSuccessMessage();
        };
    }

    private void showSuccessMessage() {
        fab.hide();
        showAlertDialog(getString(R.string.success_reservation));
    }

    private void showFailedMessage() {
        fab.clearAnimation();
        fab.setEnabled(true);
        showAlertDialog(getString(R.string.failed_success));
    }

    private void showAlertDialog(String message){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                (dialog, which) -> dialog.dismiss());
        alertDialog.show();
    }

}