package b2w.com.br.olodjinha.productdetail;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import javax.inject.Inject;

import b2w.com.br.olodjinha.R;
import b2w.com.br.olodjinha.data.models.ProductDTO;
import b2w.com.br.olodjinha.injection.DaggerProductDetailComponent;
import b2w.com.br.olodjinha.injection.ProductDetailComponent;
import b2w.com.br.olodjinha.util.CurrencyUtil;
import b2w.com.br.olodjinha.util.GlideUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductDetailActivity extends MvpActivity<ProductDetailContract, ProductDetailPresenter>
        implements ProductDetailContract {

    public static final String INFOS = "infos";

    ProductDTO mProductDTO;

    @Inject
    ProductDetailComponent mInjector;

    @BindView(R.id.imageview_product_image)
    ImageView mProductImage;

    @BindView(R.id.textview_price)
    TextView mPrice;

    @BindView(R.id.textview_price_with_discount)
    TextView mPriceWithDiscount;

    @BindView(R.id.textview_description)
    TextView mDescription;

    @BindView(R.id.textview_name)
    TextView mName;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mInjector = DaggerProductDetailComponent
                .builder()
                .build();
        mInjector.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ButterKnife.bind(this);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Carregando...");
        showProgress(true);

        mProductDTO = (ProductDTO) getIntent().getExtras().get(INFOS);
        getPresenter().getProductById(mProductDTO.getId());

    }

    @Override
    public void showProgress(boolean show) {
        if(show) {
            mProgressDialog.show();
        } else {
            mProgressDialog.dismiss();
        }
    }

    @NonNull
    @Override
    public ProductDetailPresenter createPresenter() {
        return mInjector.presenter();
    }

    @Override
    public void showProductDetails(ProductDTO product) {
        GlideUtil.setBannerImage(this, mProductImage, product.getUrl());
        mName.setText(product.getName());
        mDescription.setText(Html.fromHtml(product.getDescription()));
        mPrice.setText(CurrencyUtil.formatBigDecimalToCurrency(product.getPrice()));
        mPriceWithDiscount.setText(CurrencyUtil.formatBigDecimalToCurrency(product.getPriceWithDiscount()));
        showProgress(false);
    }

    @Override
    public void showSuccessfulReservation() {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setMessage(R.string.product_detail_reserved_successfully)
                .setPositiveButton(R.string.product_detail_ok, (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    finish();
                }).create();

        alertDialog.show();
    }

    @OnClick(R.id.imageview_back)
    public void onBackClicked() {
        finish();
    }

    @OnClick(R.id.floating_button_reserve)
    public void onReserveClicked() {
        getPresenter().doReservation(mProductDTO.getId());
    }
}
