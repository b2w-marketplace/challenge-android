package b2w.com.alodjinha.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import b2w.com.alodjinha.Interface.ItemClickListener;
import b2w.com.alodjinha.Model.Category;
import b2w.com.alodjinha.R;
import b2w.com.alodjinha.ShowCategory;
import b2w.com.alodjinha.Util.Common;
import b2w.com.alodjinha.ViewHolder.ShowCategoryViewHolder;

public class CategoriesAdapter extends RecyclerView.Adapter<ShowCategoryViewHolder> {

    private Context context;
    private List<Category> listCategories;

    public CategoriesAdapter(Context context, List<Category> listCategories) {
        this.context = context;
        this.listCategories = listCategories;
    }

    @Override
    public ShowCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate( R.layout.layout_category, parent, false );

        return new ShowCategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ShowCategoryViewHolder holder, final int position) {

        final Category category = listCategories.get(position);

        Picasso.with( context ).load( category.getUrlImage() ).into( holder.image_category );
        holder.name_category.setText( category.getDescription() );

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {

                Intent intent = new Intent( context, ShowCategory.class );
                Common.selectedCategory = category;
                context.startActivity( intent );

            }
        });


    }

    @Override
    public int getItemCount() {
        return listCategories.size();
    }

    public void removeItem( int position ){
        listCategories.remove(position);
    }

    public void restoreItem( Category item, int position ){
        listCategories.add(position, item);
    }

    public Category getItem(int position){
        return listCategories.get(position);
    }

}