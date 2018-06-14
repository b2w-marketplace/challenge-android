package br.com.soulblighter.lodjinha.ui.main.home;

import android.content.Intent;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.soulblighter.lodjinha.R;
import br.com.soulblighter.lodjinha.databinding.HomeFragmentBinding;
import br.com.soulblighter.lodjinha.rest.LodjinhaRetrofigConfig;
import br.com.soulblighter.lodjinha.rest.LodjinhaService;
import br.com.soulblighter.lodjinha.ui.detail.DetailActivity;
import br.com.soulblighter.lodjinha.ui.list.ListActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class HomeFragment extends Fragment {
    private HomeFragmentBinding mBinding;
    private SliderImageAdapter mSliderImageAdapter;
    private CategoriaAdapter mCategoriaAdapter;
    private ProdutoAdapter mProdutoAdapter;

    private LodjinhaService mLodjinhaService;
    private CompositeDisposable mDisposables;

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.home_fragment,
                container, false);

        mDisposables = new CompositeDisposable();
        mSliderImageAdapter = new SliderImageAdapter(getActivity());
        mCategoriaAdapter = new CategoriaAdapter(getActivity());
        mProdutoAdapter = new ProdutoAdapter(getActivity());
        mLodjinhaService = new LodjinhaRetrofigConfig(getContext()).getLodjinhaService();

        RecyclerView.LayoutManager verticalLayoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        RecyclerView.LayoutManager horizontalLayoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        mBinding.categoriasRecyclerview.setAdapter(mCategoriaAdapter);
        mBinding.maisVendidosRecyclerview.setAdapter(mProdutoAdapter);
        mBinding.categoriasRecyclerview.setLayoutManager(horizontalLayoutManager);
        mBinding.maisVendidosRecyclerview.setLayoutManager(verticalLayoutManager);

        mDisposables.add(mLodjinhaService.getBanners()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bannerResp -> {
                            mBinding.progressBanner.setVisibility(View.GONE);
                            mSliderImageAdapter.setBanners(bannerResp.getData());
                            mBinding.sliderIndicator.setViewPager(mBinding.sliderPager);
                        },
                        Throwable::printStackTrace)
        );

        mDisposables.add(mLodjinhaService.getCategorias()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(categoriaResp -> {
                            mBinding.progressCategoria.setVisibility(View.GONE);
                            mCategoriaAdapter.setData(categoriaResp.getData());
                            mCategoriaAdapter.setListenner(categoria -> {
                                Intent intent = new Intent(getActivity(), ListActivity.class);
                                intent.putExtra(ListActivity.EXTRA_CATEGORIA, categoria);
                                startActivity(intent);
                            });
                        },
                        Throwable::printStackTrace)
        );

        mDisposables.add(mLodjinhaService.getMaisVendido()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(maisVendidoResp -> {
                            mBinding.progressMaisVendidos.setVisibility(View.GONE);
                            mProdutoAdapter.setData(maisVendidoResp.getData());
                            mProdutoAdapter.setListenner(produto -> {
                                Intent intent = new Intent(getActivity(), DetailActivity.class);
                                intent.putExtra(DetailActivity.EXTRA_PRODUTO, produto);
                                startActivity(intent);
                            });
                        },
                        Throwable::printStackTrace)
        );

        mBinding.sliderPager.setAdapter(mSliderImageAdapter);

        // Needed for API 15 support
        ViewCompat.setElevation(mBinding.sliderPager, 8.0f * Resources.getSystem().getDisplayMetrics().density);
        ViewCompat.setElevation(mBinding.sliderIndicator, 8.0f * Resources.getSystem().getDisplayMetrics().density);
        ViewCompat.setElevation(mBinding.progressBanner, 8.0f * Resources.getSystem().getDisplayMetrics().density);
        ViewCompat.setNestedScrollingEnabled(mBinding.maisVendidosRecyclerview, false);

        return mBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        if (mDisposables != null && !mDisposables.isDisposed()) {
            mDisposables.dispose();
        }
        super.onDestroyView();
    }
}
