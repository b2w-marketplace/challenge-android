package b2w.com.br.olodjinha.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import b2w.com.br.olodjinha.GlideApp;
import b2w.com.br.olodjinha.R;
import b2w.com.br.olodjinha.data.models.CategoryDTO;
import b2w.com.br.olodjinha.listener.OnItemSelected;
import b2w.com.br.olodjinha.util.GlideUtil;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesAdapter extends RecyclerView.Adapter {

    private final List<CategoryDTO> mCategories;
    private final Context mContext;
    private final OnItemSelected mListener;

    public CategoriesAdapter(Context context, List<CategoryDTO> data, OnItemSelected onItemSelected) {
        mCategories = data;
        mContext = context;
        mListener = onItemSelected;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.category_item, viewGroup, false);

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final ViewHolder holder = (ViewHolder) viewHolder;
        final int adapterPosition = holder.getAdapterPosition();

        GlideUtil.setBannerImage(mContext, holder.getCategoryImage(),
                mCategories.get(adapterPosition).getUrl());

        holder.getCategoryName().setText(mCategories.get(adapterPosition).getDescription());
        holder.getContainer().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemSelected(mCategories.get(adapterPosition));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageView_category)
        ImageView mCategoryImage;

        @BindView(R.id.textview_category)
        TextView mCategoryName;

        @BindView(R.id.container)
        View mContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public ImageView getCategoryImage() {
            return mCategoryImage;
        }

        public TextView getCategoryName() {
            return mCategoryName;
        }

        public View getContainer() {
            return mContainer;
        }
    }
}
