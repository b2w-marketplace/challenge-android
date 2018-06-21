package com.olodjinha.guilherme.alodjinha.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.olodjinha.guilherme.alodjinha.R;
import com.olodjinha.guilherme.alodjinha.model.Category;

import java.util.List;

/**
 * Created by Guilherme on 20/06/2018.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    public static CategoryAdapter getInstance() {
        return mInstance;
    }

    private Context mContext;
    public List<Category> categoryList;
    private static CategoryAdapter.MyClickListener myClickListener;
    private static CategoryAdapter mInstance;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textViewCategory;
        public ImageView imageViewCategory;

        public MyViewHolder(View view) {
            super(view);
            textViewCategory = view.findViewById(R.id.textViewCategory);
            imageViewCategory = view.findViewById(R.id.imageViewCategory);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(CategoryAdapter.MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public CategoryAdapter(Context mContext, List<Category> categoryList) {
        this.mContext = mContext;
        this.categoryList = categoryList;
        mInstance = this;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_adapter, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        Category category = categoryList.get(position);
        holder.textViewCategory.setText(category.getDescricao());
        Glide.with(mContext).load(category.getUrlImagem()).into(holder.imageViewCategory);
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}