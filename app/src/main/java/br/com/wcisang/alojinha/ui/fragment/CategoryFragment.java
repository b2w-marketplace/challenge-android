package br.com.wcisang.alojinha.ui.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.wcisang.alojinha.R;
import br.com.wcisang.alojinha.model.Category;
import br.com.wcisang.alojinha.ui.adapter.IntroCategoryAdapter;
import br.com.wcisang.alojinha.viewmodel.CategoryFragmentViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by WCisang on 07/06/2018.
 */
public class CategoryFragment extends Fragment {

    private CategoryFragmentViewModel viewModel;

    @BindView(R.id.recyclerview_category)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.category_fragment, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(CategoryFragmentViewModel.class);
        viewModel.getListMutableLiveData().observe(this, getCategoryListObserver());
        viewModel.init();
    }

    private Observer<List<Category>> getCategoryListObserver() {
        return new Observer<List<Category>>() {
            @Override
            public void onChanged(@Nullable List<Category> categories) {
                if (categories != null)
                    setupList(categories);
            }
        };
    }

    private void setupList(List<Category> categories){
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);
        IntroCategoryAdapter adapter = new IntroCategoryAdapter(categories);
        recyclerView.setAdapter(adapter);
    }
}
