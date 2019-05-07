package com.mmart.sidershopping.view.homepage;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mmart.sidershopping.ItemClickListenerCategory;
import com.mmart.sidershopping.R;
import com.mmart.sidershopping.model.entity.Category;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<Category> categories = new ArrayList<>();
    private ItemClickListenerCategory itemClickListener;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Category category = categories.get(position);

        if (category != null) {
            holder.bindTo(category);
        }
    }

    @Override
    public int getItemCount() {

        if (categories == null) {
            return 0;
        }
        return categories.size();
    }

    public void submitList(List<Category> categories) {
        this.categories = categories;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView image;
        TextView name;
        Category currentCategory;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.category_image);
            name = itemView.findViewById(R.id.category_name);
            itemView.setOnClickListener(this);
        }

        public void bindTo(Category category) {
            currentCategory = category;

            Picasso.get()
                    .load(category.getUrlImagem())
                    .fit()
                    .centerCrop()
                    .into(image);

            name.setText(category.getDescricao());
        }

        @Override
        public void onClick(View view) {
            if(itemClickListener != null) {
                itemClickListener.onItemClick(currentCategory);
            }
        }
    }

    public void setOnItemClickListener(ItemClickListenerCategory itemClickListener){
        this.itemClickListener = itemClickListener;
    }
}