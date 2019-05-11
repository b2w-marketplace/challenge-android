package br.com.douglas.fukuhara.lodjinha.presenter;

import java.lang.ref.WeakReference;
import java.util.List;

import br.com.douglas.fukuhara.lodjinha.interfaces.ProductsByListCategoryContract;
import br.com.douglas.fukuhara.lodjinha.network.RestClient;
import br.com.douglas.fukuhara.lodjinha.network.vo.ProductDataVo;
import br.com.douglas.fukuhara.lodjinha.network.vo.ProductVo;
import io.reactivex.disposables.CompositeDisposable;

import static br.com.douglas.fukuhara.lodjinha.network.NetworkUtils.getObservableNetworkThread;

public class ProductsListByCategoryPresenter implements ProductsByListCategoryContract.Presenter {

    private final int LIMIT_OF_DATA_RETRIEVED = 20;
    private int mCurrentOffest;
    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private WeakReference<ProductsByListCategoryContract.View> mView;
    private RestClient mRestClient;
    private Integer mCategoryId;
    private boolean doesStillHavePossibleDataOnServer;

    private List<ProductDataVo> mListOfProducts;

    public ProductsListByCategoryPresenter(ProductsByListCategoryContract.View view,
                                           RestClient restClient,
                                           Integer categoryId) {
        mView = new WeakReference<>(view);
        mRestClient = restClient;
        mCategoryId = categoryId;
        mCurrentOffest = 0;
        doesStillHavePossibleDataOnServer = true;
    }

    @Override
    public void fetchData() {
        if (doesStillHavePossibleDataOnServer) {
            if (mView.get() != null) {
                mView.get().showLoader();
            }
            mCompositeDisposable.add(
                    mRestClient.getApi().getProduto(mCurrentOffest, LIMIT_OF_DATA_RETRIEVED, mCategoryId)
                            .compose(getObservableNetworkThread())
                            .subscribe(
                                    this::onProductListFetchSuccess,
                                    this::onProductListFetchError));
        }
    }

    private void onProductListFetchSuccess(ProductVo productVo) {
        List<ProductDataVo> listOfData = productVo.getData();

        if (mView.get() != null) {
            if (mCurrentOffest == 0) {
                mListOfProducts = listOfData;
            } else {
                mListOfProducts.addAll(listOfData);
            }
            mView.get().dismissLoader();
            mView.get().showRecyclerViewContainer();
            mView.get().displayListOfProducts(mListOfProducts);
        }
        if (listOfData.size() < LIMIT_OF_DATA_RETRIEVED) {
            doesStillHavePossibleDataOnServer = false;
        } else if (listOfData.size() == LIMIT_OF_DATA_RETRIEVED) {
            mCurrentOffest = mCurrentOffest + LIMIT_OF_DATA_RETRIEVED;
        }
    }

    private void onProductListFetchError(Throwable throwable) {
        if (mView.get() != null) {
            mView.get().dismissLoader();
        }
    }

    @Override
    public void disposeAll() {
        mCompositeDisposable.clear();

    }
}
