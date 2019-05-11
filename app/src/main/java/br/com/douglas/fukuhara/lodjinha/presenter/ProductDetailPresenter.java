package br.com.douglas.fukuhara.lodjinha.presenter;

import java.lang.ref.WeakReference;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

import br.com.douglas.fukuhara.lodjinha.interfaces.ProductDetailContract;
import br.com.douglas.fukuhara.lodjinha.network.vo.ProductDataVo;

public class ProductDetailPresenter implements ProductDetailContract.Presenter {

    private ProductDataVo mModel;
    private WeakReference<ProductDetailContract.View> mView;

    public ProductDetailPresenter(ProductDataVo model, ProductDetailContract.View view) {
        mModel = model;
        mView = new WeakReference<>(view);
    }

    @Override
    public void displayProductDetail() {

        if (mView.get() != null && mModel != null) {

            if (mModel != null) {
                NumberFormat numberFormatter = NumberFormat.getInstance(new Locale("pt", "BR"));
                numberFormatter.setRoundingMode(RoundingMode.HALF_UP);
                numberFormatter.setMaximumFractionDigits(2);
                numberFormatter.setMinimumFractionDigits(2);

                mView.get().populateProductLayout(mModel.getUrlImagem(),
                        mModel.getNome(),
                        numberFormatter.format(mModel.getPrecoDe()),
                        numberFormatter.format(mModel.getPrecoPor()),
                        mModel.getDescricao());
            } else {
                mView.get().displayNoContentAvailable();
            }
        }
    }
}
