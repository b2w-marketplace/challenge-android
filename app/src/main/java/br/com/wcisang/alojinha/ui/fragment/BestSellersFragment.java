package br.com.wcisang.alojinha.ui.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.wcisang.alojinha.R;
import br.com.wcisang.alojinha.model.Product;
import br.com.wcisang.alojinha.ui.activity.ProductDetailActivity;
import br.com.wcisang.alojinha.ui.adapter.IntroCategoryAdapter;
import br.com.wcisang.alojinha.ui.adapter.ProductAdapter;
import br.com.wcisang.alojinha.viewmodel.BestSellersFragmentViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by WCisang on 07/06/2018.
 */
public class BestSellersFragment extends Fragment {


    private BestSellersFragmentViewModel viewModel;

    @BindView(R.id.recyclerview_product)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

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
        return this::setupList;
    }

    private void setupList(List<Product> products){
        progressBar.setVisibility(View.GONE);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        ProductAdapter adapter = new ProductAdapter(products, this::callDetail);
        recyclerView.setAdapter(adapter);
    }

    private void callDetail(Product product){
        Intent it = new Intent(getActivity(), ProductDetailActivity.class);
        it.putExtra(ProductDetailActivity.PRODUCT, product);
        startActivity(it);
    }
}
