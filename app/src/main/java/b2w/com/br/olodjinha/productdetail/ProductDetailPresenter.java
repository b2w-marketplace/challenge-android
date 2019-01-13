package b2w.com.br.olodjinha.productdetail;

import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import javax.inject.Inject;

import b2w.com.br.olodjinha.data.api.DataFacade;
import b2w.com.br.olodjinha.data.models.ProductDTO;
import b2w.com.br.olodjinha.data.models.ProductsWrapper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ProductDetailPresenter extends MvpNullObjectBasePresenter<ProductDetailContract> {

    @Inject
    public ProductDetailPresenter() {
    }

    public void getProductById(Integer productId) {
        DataFacade.getProductById(productId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(product -> getView().showProductDetails(product),
                        throwable -> Log.e("ERROR", throwable.getMessage()));
    }

    public void doReservation(Integer productId) {
        DataFacade.doReservation(productId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> getView().showProgress(true))
                .doOnDispose(() -> getView().showProgress(false))
                .subscribe(object -> getView().showSuccessfulReservation(),
                        throwable -> Log.e("ERROR", throwable.getMessage()));
    }
}
