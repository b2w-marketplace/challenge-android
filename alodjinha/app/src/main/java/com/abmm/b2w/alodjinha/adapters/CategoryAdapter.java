package com.abmm.b2w.alodjinha.adapters;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abmm.b2w.alodjinha.R;
import com.abmm.b2w.alodjinha.activities.main.IMainPresenter;
import com.abmm.b2w.alodjinha.model.Category;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryVH> {

    private final IMainPresenter ctx;

    public CategoryAdapter(IMainPresenter context) {
        this.ctx = context;
    }

    @NonNull
    @Override
    public CategoryVH onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_main_category, parent, false);
        return new CategoryVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryVH holder, int position) {
        final Category element = ctx.getCategoryList().get(position);
        holder.bind(element);
    }

    @Override
    public int getItemCount() {
        return ctx.getCategoryList().size();
    }

    class CategoryVH extends RecyclerView.ViewHolder {
        @BindView(R.id.element_category_icon) ImageView mIcon;
        @BindView(R.id.element_category_title) TextView mCategory;
        @BindView(R.id.element_category_layout) ConstraintLayout mLayout;

        final View view;
        Category category;

        CategoryVH(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;
        }

        void bind(Category item) {
            this.category = item;

            setImage(item);
            mCategory.setText(item.getDescription());
        }

        void setImage(Category item) {
            Glide.with(view)
                    .load(item.getPictUrl())
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.loading_banner_image)
                            .error(R.drawable.logo_menu))
                    .into(mIcon);
        }

        @OnLongClick(R.id.element_category_layout)
        boolean showElementName() {
            Toast.makeText(ctx.getContext(),category.getDescription(), Toast.LENGTH_SHORT).show();
            return true;
        }

        @OnClick(R.id.element_category_layout)
        void openCategory(View v) {
            Toast.makeText(ctx.getContext(), "Abrindo activity...", Toast.LENGTH_SHORT).show();
        }
    }
}
