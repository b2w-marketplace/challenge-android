package br.com.douglas.fukuhara.lodjinha.network;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public final class NetworkUtils {

    public static <T> ObservableTransformer<T, T> getObservableNetworkThread() {
        return observable -> observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
