package br.com.wcisang.alojinha.ui.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.wcisang.alojinha.R;
import br.com.wcisang.alojinha.model.Product;
import br.com.wcisang.alojinha.viewmodel.BestSellersFragmentViewModel;
import butterknife.ButterKnife;

/**
 * Created by WCisang on 07/06/2018.
 */
public class BestSellersFragment extends Fragment {


    private BestSellersFragmentViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.seller_fragment, container, false);
        ButterKnife.bind(this,v);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(BestSellersFragmentViewModel.class);
        viewModel.getListMutableLiveData().observe(this, getBestSellersObserver());
        viewModel.init();
    }

    private Observer<List<Product>> getBestSellersObserver() {
        return new Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable List<Product> products) {

            }
        };
    }
}
