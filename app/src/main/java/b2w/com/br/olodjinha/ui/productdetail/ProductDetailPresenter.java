package b2w.com.br.olodjinha.ui.productdetail;

import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import javax.inject.Inject;

import b2w.com.br.olodjinha.data.api.DataFacade;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProductDetailPresenter extends MvpNullObjectBasePresenter<ProductDetailContract> {

    private final DataFacade mDataFacade;

    Disposable mReservationSubscribe;
    Disposable mProductsubscribe;

    @Inject
    public ProductDetailPresenter(DataFacade dataFacade) {
        mDataFacade = dataFacade;
    }

    public void getProductById(Integer productId) {
        mProductsubscribe = mDataFacade.getProductById(productId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(product -> getView().showProductDetails(product),
                        throwable -> getView().showError());
    }

    public void doReservation(Integer productId) {
        mReservationSubscribe = mDataFacade.doReservation(productId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> getView().showProgress(true))
                .doOnDispose(() -> getView().showProgress(false))
                .subscribe(object -> getView().showSuccessfulReservation(),
                        throwable -> getView().showError());
    }

    @Override
    public void detachView(boolean retainInstance) {
        if (mReservationSubscribe != null) {
            mReservationSubscribe.dispose();
        }
        if (mProductsubscribe != null) {
            mProductsubscribe.dispose();
        }
        super.detachView(retainInstance);
    }
}
