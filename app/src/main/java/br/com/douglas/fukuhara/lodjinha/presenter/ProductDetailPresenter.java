package br.com.douglas.fukuhara.lodjinha.presenter;

import java.lang.ref.WeakReference;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

import br.com.douglas.fukuhara.lodjinha.interfaces.ProductDetailContract;
import br.com.douglas.fukuhara.lodjinha.network.RestClient;
import br.com.douglas.fukuhara.lodjinha.network.vo.ProductDataVo;
import br.com.douglas.fukuhara.lodjinha.network.vo.ResultVo;
import io.reactivex.disposables.CompositeDisposable;

import static br.com.douglas.fukuhara.lodjinha.network.NetworkUtils.getObservableNetworkThread;

public class ProductDetailPresenter implements ProductDetailContract.Presenter {

    private final String SERVER_RESERVATION_SUCCESS_MESSAGE = "success";

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private ProductDataVo mModel;
    private WeakReference<ProductDetailContract.View> mView;
    private RestClient mClient;

    public ProductDetailPresenter(ProductDataVo model, ProductDetailContract.View view, RestClient client) {
        mModel = model;
        mView = new WeakReference<>(view);
        mClient = client;
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

    @Override
    public void onReserveButtonPressed() {
        if (mView.get() != null) {
            mView.get().setFabEnabled(false);
        }
        if (mModel != null) {
            mCompositeDisposable.add(
                    mClient.getApi().reserveProductId(mModel.getId().toString())
                    .compose(getObservableNetworkThread())
                    .subscribe(
                            this::onReserveSuccess,
                            this::onServerError));
        }
    }

    private void onReserveSuccess(ResultVo resultVo) {
        if (resultVo != null) {
            if (mView.get() != null) {
                if (SERVER_RESERVATION_SUCCESS_MESSAGE.equals(resultVo.getResult().toLowerCase())) {
                    mView.get().showReservationSuccess();
                } else {
                    String serverMessage = resultVo.getMensagem();
                    if (serverMessage != null && !serverMessage.trim().isEmpty()) {
                        mView.get().showFailureOnReservation(serverMessage);
                    } else {
                        mView.get().showGenericFailureOnReservation();
                    }
                }
            }
        }
    }

    @Override
    public void onDialogConfirmationPressed() {
        if (mView.get() != null) {
            mView.get().setFabEnabled(true);
        }

    }

    private void onServerError(Throwable throwable) {
        if (mView.get() != null) {
            mView.get().setFabEnabled(true);
        }
    }
    @Override
    public void disposeAll() {
        mCompositeDisposable.clear();
    }
}
