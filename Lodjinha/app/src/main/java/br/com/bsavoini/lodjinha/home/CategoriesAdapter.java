package br.com.bsavoini.lodjinha.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.bsavoini.lodjinha.R;
import br.com.bsavoini.lodjinha.api.model.CategoryModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> {
    private List<CategoryModel> categoriesArr;
    private RequestManager glideRequest;

    public CategoriesAdapter(List<CategoryModel> categoriesArr) {
        this.categoriesArr = categoriesArr;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        glideRequest = Glide.with(viewGroup.getContext());
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_category, viewGroup, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder viewHolder, int i) {
        CategoryModel categoryModel = categoriesArr.get(viewHolder.getAdapterPosition());
        viewHolder.categoryTxt.setText(categoryModel.getDescription());

        glideRequest
                .load(categoryModel.getImageURL())
                .into(viewHolder.categoryImg);
    }

    @Override
    public int getItemCount() {
        return categoriesArr.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        private TextView categoryTxt;
        private ImageView categoryImg;

        CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTxt = itemView.findViewById(R.id.txt_category);
            categoryImg = itemView.findViewById(R.id.img_category);
        }
    }
}
