package br.com.wcisang.alojinha.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.wcisang.alojinha.databinding.ItemIntroCategoryBinding;
import br.com.wcisang.alojinha.model.Category;

/**
 * Created by WCisang on 07/06/2018.
 */
public class IntroCategoryAdapter extends RecyclerView.Adapter<IntroCategoryAdapter.CategoryViewHolder> {

    List<Category> categories;
    CategoryListener listener;

    public IntroCategoryAdapter(List<Category> categories, CategoryListener categoryListener) {
        this.categories = categories;
        this.listener = categoryListener;
    }

    public interface CategoryListener {
        void onCategoryClick(Category category);
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemIntroCategoryBinding binding = ItemIntroCategoryBinding.inflate(inflater, parent, false);
        return new CategoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.bind(categories.get(position));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        private ItemIntroCategoryBinding binding;

        public CategoryViewHolder(ItemIntroCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Category category){
            binding.setCategory(category);
            itemView.setOnClickListener((View v) -> listener.onCategoryClick(category));
            binding.executePendingBindings();
        }
    }
}
