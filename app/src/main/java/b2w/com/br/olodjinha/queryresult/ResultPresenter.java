package b2w.com.br.olodjinha.queryresult;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import b2w.com.br.olodjinha.data.api.DataFacade;
import b2w.com.br.olodjinha.data.models.ProductsWrapper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ResultPresenter extends MvpNullObjectBasePresenter<ResultContract> {

    @Inject
    public ResultPresenter() {
    }

    public void getProducts(Integer categoryId, Integer page) {
        DataFacade.queryProducts(getQuery(categoryId, page))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ProductsWrapper>() {
                    @Override
                    public void accept(ProductsWrapper bestSellersWrapper) throws Exception {
                        getView().showProducts(bestSellersWrapper.getData());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    private Map<String, Object> getQuery(Integer categoryId, Integer page) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("categoriaId", categoryId);
        queryMap.put("offset", page);
        queryMap.put("limit", 20);
        return queryMap;
    }
}
