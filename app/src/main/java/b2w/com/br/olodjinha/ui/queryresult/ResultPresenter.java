package b2w.com.br.olodjinha.ui.queryresult;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import b2w.com.br.olodjinha.data.api.DataFacade;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ResultPresenter extends MvpNullObjectBasePresenter<ResultContract> {

    public static final String CATEGORIA_ID = "categoriaId";
    public static final String OFFSET = "offset";
    public static final String LIMIT = "limit";

    private final DataFacade mDataFacade;

    private Disposable subscribe;

    @Inject
    public ResultPresenter(DataFacade dataFacade) {
        mDataFacade = dataFacade;
    }

    public void getProducts(Integer categoryId, Integer page) {
        subscribe = mDataFacade.getProductsByCategory(getQuery(categoryId, page))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bestSellersWrapper -> getView().showProducts(bestSellersWrapper.getData()),
                        throwable -> {
                            getView().showError();
                        });
    }

    private Map<String, Object> getQuery(Integer categoryId, Integer page) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put(CATEGORIA_ID, categoryId);
        queryMap.put(OFFSET, page);
        queryMap.put(LIMIT, 20);
        return queryMap;
    }

    @Override
    public void detachView(boolean retainInstance) {
        if (subscribe != null) {
            subscribe.dispose();
        }
        super.detachView(retainInstance);
    }
}
