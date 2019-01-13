package b2w.com.br.olodjinha.queryresult;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import b2w.com.br.olodjinha.productdetail.ProductDetailActivity;
import b2w.com.br.olodjinha.R;
import b2w.com.br.olodjinha.data.models.ProductDTO;
import b2w.com.br.olodjinha.data.models.CategoryDTO;
import b2w.com.br.olodjinha.injection.DaggerResultComponent;
import b2w.com.br.olodjinha.injection.ResultComponent;
import b2w.com.br.olodjinha.injection.ScreenFlowModule;
import b2w.com.br.olodjinha.listener.OnItemSelected;
import b2w.com.br.olodjinha.main.BestSellersAdapter;
import b2w.com.br.olodjinha.screenflow.ChangeActivityHandler;
import b2w.com.br.olodjinha.util.EndlessRecyclerOnScrollListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResultActivity extends MvpActivity<ResultContract, ResultPresenter> implements ResultContract {

    public static final String INFOS = "infos";

    @BindView(R.id.recyclerview_result)
    RecyclerView mProductsRecylerview;

    @BindView(R.id.progress_bar)
    ProgressBar mLoadingMoreDataProgress;

    @Inject
    ResultComponent mInjector;

    @Inject
    ChangeActivityHandler mChangeActivityHandler;

    private Integer mPage = 1;
    private CategoryDTO mCategoryDTO;
    private List<ProductDTO> mItens = new ArrayList<>();
    private BestSellersAdapter mProductsAdapter;
    private EndlessRecyclerOnScrollListener mEndlessRecyclerOnScrollListener;
    private int mTotalItensAtPage;
    private boolean endOfPages;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mInjector = DaggerResultComponent
                .builder()
                .screenFlowModule(new ScreenFlowModule())
                .build();
        mInjector.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);

        mCategoryDTO = (CategoryDTO) getIntent().getExtras().get(INFOS);

        showProgressDialog();

        getPresenter().getProducts(mCategoryDTO.getId(), mPage);

        initRecyclerView();
    }

    private void showProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.general_loading));
        mProgressDialog.show();
    }

    @NonNull
    @Override
    public ResultPresenter createPresenter() {
        return mInjector.presenter();
    }

    @Override
    public void showProducts(List<ProductDTO> data) {
        mProgressDialog.dismiss();
        mTotalItensAtPage = data.size();
        mItens.addAll(data);
        mProductsAdapter.notifyDataSetChanged();
        mLoadingMoreDataProgress.setVisibility(View.GONE);
    }

    public void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mProductsAdapter = new BestSellersAdapter(this, mItens, new OnItemSelected() {
            @Override
            public void onItemSelected(Serializable serializable) {
                mChangeActivityHandler.startActivityWithExtra(ResultActivity.this, ProductDetailActivity.class, serializable);
            }
        });

        mEndlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener(layoutManager, mProductsRecylerview) {
            @Override
            public void onLoadMore(int current_page, int distanceFromBottom) {
                if (mTotalItensAtPage < 15) {
                    endOfPages = true;
                }

                if (!endOfPages) {
                    mLoadingMoreDataProgress.setVisibility(View.VISIBLE);
                    getPresenter().getProducts(mCategoryDTO.getId(), ++mPage);
                }
            }
        };

        mProductsRecylerview.addOnScrollListener(mEndlessRecyclerOnScrollListener);
        mProductsRecylerview.setLayoutManager(layoutManager);
        mProductsRecylerview.setHasFixedSize(true);
        mProductsRecylerview.setNestedScrollingEnabled(false);
        mProductsRecylerview.setAdapter(mProductsAdapter);
    }

    @OnClick(R.id.imgview_back)
    public void onBackClicked() {
        finish();
    }
}
