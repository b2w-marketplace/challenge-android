package br.com.soulblighter.lodjinha.ui.main.home;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.com.soulblighter.lodjinha.R;
import br.com.soulblighter.lodjinha.databinding.HomeCategoriaItemBinding;
import br.com.soulblighter.lodjinha.model.Categoria;

public class CategoriaAdapter extends RecyclerView.Adapter<RecyclerView
        .ViewHolder> {

    private final String TAG = CategoriaAdapter.class.getSimpleName();

    private final Context mContext;
    private OnCategoriaClickHandler mClickHandler;
    private List<Categoria> mData = new ArrayList<>();

    public static final int VIEW_DEFAULT = 0;

    public interface OnCategoriaClickHandler {
        void onCategoriaClick(Categoria categoria);
    }

    public CategoriaAdapter(Context context) {
        mContext = context;
    }

    public void setListenner(OnCategoriaClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    public List<Categoria> getData() {
        return mData;
    }

    public void setData(List<Categoria> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int
            viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext
                ());
        switch (viewType) {
            case VIEW_DEFAULT:
            default:
                HomeCategoriaItemBinding binding = DataBindingUtil.inflate
                        (layoutInflater, R.layout.home_categoria_item, parent, false);
                return new HouseViewHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int
            position) {

        switch (holder.getItemViewType()) {
            case VIEW_DEFAULT:
                final HouseViewHolder houseHolder = (HouseViewHolder) holder;
                Categoria categoria = mData.get(position);

                if (categoria.getUrlImagem() != null) {

                    Picasso.with(mContext).load(categoria.getUrlImagem())
                        .placeholder(R.drawable.logo_sobre)
                        .into(houseHolder.binding.icon);
                }

                houseHolder.binding.text.setText(categoria.getDescricao());

                houseHolder.setData(categoria);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return VIEW_DEFAULT;
    }

    @Override
    public int getItemCount() {
        if (null == mData)
            return 0;
        return mData.size();
    }

    class HouseViewHolder extends RecyclerView.ViewHolder implements View
            .OnClickListener {
        private final HomeCategoriaItemBinding binding;
        private Categoria data;

        HouseViewHolder(HomeCategoriaItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(this);
        }

        public void setData(Categoria data) {
            this.data = data;
        }

        @Override
        public void onClick(View v) {
            if (mClickHandler != null) {
                mClickHandler.onCategoriaClick(data);
            }
        }
    }
}
