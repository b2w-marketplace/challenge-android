package freitas.dias.lodjinha.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import freitas.dias.lodjinha.R;
import freitas.dias.lodjinha.api.model.Category;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder>{

    private List<Category> categories;
    private Context context;

    public CategoriesAdapter(Context context, List<Category> categories) {
        this.categories = categories;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_categories, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Category category = categories.get(position);

        if (!TextUtils.isEmpty(category.getUrlImage())) {
            Picasso.with(context).load(category.getUrlImage())
                    .into(holder.imageView);
        }

        holder.textView.setText(category.getDescription());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        ViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.recyclerView_categories_image);
            this.textView = (TextView) view.findViewById(R.id.recyclerView_categories_name);
        }
    }
}
