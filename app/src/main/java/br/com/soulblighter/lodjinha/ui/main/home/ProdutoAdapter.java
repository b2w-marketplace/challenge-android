package br.com.soulblighter.lodjinha.ui.main.home;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.soulblighter.lodjinha.R;
import br.com.soulblighter.lodjinha.databinding.AllProdutoItemBinding;
import br.com.soulblighter.lodjinha.databinding.AllProdutoLoadingBinding;
import br.com.soulblighter.lodjinha.model.Produto;

public class ProdutoAdapter extends RecyclerView.Adapter<RecyclerView
        .ViewHolder> {

    private final Context mContext;
    private OnProdutoClickHandler mClickHandler;
    private List<Produto> mData = new ArrayList<>();
    private boolean mLoading = false;

    public static final int VIEW_DEFAULT = 0;
    public static final int VIEW_LOADING = 1;

    public interface OnProdutoClickHandler {
        void onProdutoClick(Produto produto);
    }

    public ProdutoAdapter(Context context) {
        mContext = context;
    }

    public void setListenner(OnProdutoClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    public List<Produto> getData() {
        return mData;
    }

    public void setData(List<Produto> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void pushData(List<Produto> data) {
        if(mData==null)mData=new ArrayList<>();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void pushData(Produto data) {
        if(mData==null)mData=new ArrayList<>();
        mData.add(data);
        notifyDataSetChanged();
    }

    public void setLoading(boolean value) {
        mLoading = value;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return mLoading && position==mData.size() ? VIEW_LOADING : VIEW_DEFAULT;
    }

    @Override
    public int getItemCount() {
        return mData==null||mData.size()==0?0:(mData.size() + (mLoading?1:0));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int
            viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case VIEW_LOADING:
                AllProdutoLoadingBinding binding = DataBindingUtil.inflate
                        (layoutInflater, R.layout.all_produto_loading, parent, false);
                return new LoadingViewHolder(binding);
            case VIEW_DEFAULT:
            default:
                AllProdutoItemBinding binding2 = DataBindingUtil.inflate
                        (layoutInflater, R.layout.all_produto_item, parent, false);
                return new DefaultViewHolder(binding2);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int
            position) {

        switch (getItemViewType(position)) {
            case VIEW_LOADING:
                break;
            case VIEW_DEFAULT:
                final DefaultViewHolder houseHolder = (DefaultViewHolder) holder;
                Produto produto = mData.get(position);

                if (produto.getUrlImagem() != null) {
                    Picasso.with(mContext).load(produto.getUrlImagem()).into
                            (houseHolder.binding.icon);
                }

                houseHolder.binding.descricao.setText(produto.getNome());
                houseHolder.binding.precoFinal.setText(
                        mContext.getString(R.string.produto_preco_por,
                                String.format(Locale.US, "%.02f", produto.getPrecoPor())));
                houseHolder.binding.precoOriginal.setText(
                        mContext.getString(R.string.produto_preco_de,
                                String.format(Locale.US, "%.02f", produto.getPrecoDe())));
                houseHolder.binding.precoOriginal.setPaintFlags(
                        houseHolder.binding.precoOriginal.getPaintFlags() |
                                Paint.STRIKE_THRU_TEXT_FLAG);

                houseHolder.setData(produto);
                break;
        }
    }

    class DefaultViewHolder extends RecyclerView.ViewHolder implements View
            .OnClickListener {
        private final AllProdutoItemBinding binding;
        private Produto data;

        DefaultViewHolder(AllProdutoItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(this);
        }

        public void setData(Produto data) {
            this.data = data;
        }

        @Override
        public void onClick(View v) {
            if (mClickHandler != null) {
                mClickHandler.onProdutoClick(data);
            }
        }
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {
        private final AllProdutoLoadingBinding binding;

        LoadingViewHolder(AllProdutoLoadingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
