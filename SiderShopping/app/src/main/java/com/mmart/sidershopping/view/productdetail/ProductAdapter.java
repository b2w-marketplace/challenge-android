package com.mmart.sidershopping.view.productdetail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mmart.sidershopping.ItemClickListener;
import com.mmart.sidershopping.R;
import com.mmart.sidershopping.model.entity.Product;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> mProducts;
    private Context mContext;
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    private static ItemClickListener itemClickListener;


    public ProductAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Product product = mProducts.get(position);

        if (product != null) {
            holder.bindTo(product);
        }
    }

    @Override
    public int getItemCount() {
        if (mProducts == null) {
            return 0;
        }
        return mProducts.size();
    }

    public void submitList(List<Product> products) {
        mProducts = products;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView image;
        TextView name;
        TextView oldPrice;
        TextView newPrice;
        Product currentProduct;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.product_image);
            name = itemView.findViewById(R.id.product_name);
            newPrice = itemView.findViewById(R.id.product_new_price);
            oldPrice = itemView.findViewById(R.id.product_old_price);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if(itemClickListener != null) {
                itemClickListener.onItemClick(currentProduct);
            }
        }

        public void bindTo(Product product) {

            currentProduct = product;
            
            Picasso.get()
                    .load(product.getUrlImagem())
                    .fit()
                    .centerCrop()
                    .into(image);

            name.setText(product.getNome());
            oldPrice.setText("De: " + df2.format(product.getPrecoDe()));
            newPrice.setText("Por " + df2.format(product.getPrecoPor()));
        }
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
}
