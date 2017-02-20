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

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import freitas.dias.lodjinha.R;
import freitas.dias.lodjinha.api.model.Product;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder>{

    private List<Product> products;
    private Context context;


    public ProductsAdapter(Context context, List<Product> products) {
        this.products = products;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_product, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product category = products.get(position);

        if (!TextUtils.isEmpty(category.getUrlImage())) {
            Picasso.with(context).load(category.getUrlImage())
                    .into(holder.imageView);
        }

        holder.textViewDesc.setText(category.getDescription());
        NumberFormat currency = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        holder.textViewFor.setText(currency.format(category.getPriceFor()));
        holder.textViewOf.setText(currency.format(category.getPriceOf()));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewDesc;
        TextView textViewFor;
        TextView textViewOf;

        ViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.view_product_image);
            this.textViewDesc = (TextView) view.findViewById(R.id.view_product_desc_product);
            this.textViewFor = (TextView) view.findViewById(R.id.view_product_priceFor);
            this.textViewOf = (TextView) view.findViewById(R.id.view_product_priceOf);
        }
    }
}
