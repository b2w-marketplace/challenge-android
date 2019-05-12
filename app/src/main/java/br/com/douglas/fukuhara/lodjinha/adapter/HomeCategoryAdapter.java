package br.com.douglas.fukuhara.lodjinha.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import br.com.douglas.fukuhara.lodjinha.R;
import br.com.douglas.fukuhara.lodjinha.network.vo.CategoryDataVo;

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.HomeCategoryViewHolder> {

    private final List<CategoryDataVo> mCategoryList;
    private final CategoryListClickListener mListener;

    public HomeCategoryAdapter(List<CategoryDataVo> categoryList, CategoryListClickListener listener) {
        mCategoryList = categoryList;
        mListener = listener;
    }

    @NonNull
    @Override
    public HomeCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new HomeCategoryViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCategoryViewHolder holder, int position) {
        if (getItemCount() > 0) {
            holder.onBind(mCategoryList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mCategoryList == null ? 0 : mCategoryList.size();
    }

    public interface CategoryListClickListener {
        void onCategoryItemClick(CategoryDataVo categoryDataVo);
    }

    class HomeCategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ViewGroup mViewGroup;
        private final ImageView ivCategoryImage;
        private final TextView tvCategoryName;

        HomeCategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            mViewGroup = (ViewGroup) itemView;
            ivCategoryImage = itemView.findViewById(R.id.iv_category_list_image);
            tvCategoryName = itemView.findViewById(R.id.tv_category_list_desc);

            itemView.setOnClickListener(this);
        }

        void onBind(CategoryDataVo categoryDataVo) {
            String urlImagem = categoryDataVo.getUrlImagem();
            // TODO: Configure: fallback, onError, placeholder
            Glide
                    .with(mViewGroup)
                    .load(urlImagem)
                    .placeholder(R.mipmap.image_placeholder)
                    .error(R.mipmap.image_placeholder_error)
                    .into(ivCategoryImage);
            tvCategoryName.setText(categoryDataVo.getDescricao());
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            mListener.onCategoryItemClick(mCategoryList.get(position));
        }
    }
}
