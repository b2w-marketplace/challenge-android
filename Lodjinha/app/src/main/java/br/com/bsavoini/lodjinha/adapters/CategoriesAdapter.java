package br.com.bsavoini.lodjinha.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.bsavoini.lodjinha.R;
import br.com.bsavoini.lodjinha.api.model.CategoryModel;
import br.com.bsavoini.lodjinha.home.interfaces.CategoryClickCallback;
import com.squareup.picasso.Picasso;


import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> {
    private List<CategoryModel> categoriesArr;
    private Picasso picassoInstance;
    private CategoryClickCallback categoryClickCallback;

    public CategoriesAdapter(List<CategoryModel> categoriesArr, CategoryClickCallback categoryClickCallback) {
        this.categoriesArr = categoriesArr;
        this.categoryClickCallback = categoryClickCallback;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        picassoInstance = Picasso.with(viewGroup.getContext());
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_category, viewGroup, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder viewHolder, int i) {
        CategoryModel categoryModel = categoriesArr.get(viewHolder.getAdapterPosition());
        viewHolder.categoryTxt.setText(categoryModel.getDescription());

        picassoInstance.load(categoryModel.getImageURL())
                .placeholder(R.drawable.logo_navbar)
                .into(viewHolder.categoryImg);

    }

    @Override
    public int getItemCount() {
        return categoriesArr.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView categoryTxt;
        private ImageView categoryImg;

        CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTxt = itemView.findViewById(R.id.txt_category);
            categoryImg = itemView.findViewById(R.id.img_category);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            CategoryModel categoryModel = categoriesArr.get(getAdapterPosition());
            String categoryName = categoryModel.getDescription();
            int categoryId = categoryModel.getId();
            categoryClickCallback.onClickCategory(categoryName, categoryId);
        }
    }
}
