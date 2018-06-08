package br.com.wcisang.alojinha.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.function.Function;

import br.com.wcisang.alojinha.databinding.ItemBestSellerProductBinding;
import br.com.wcisang.alojinha.model.Product;

/**
 * Created by WCisang on 07/06/2018.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    List<Product> products;
    private ProductListener productListener;


    public ProductAdapter(List<Product> products, ProductListener productListener) {
        this.products = products;
        this.productListener = productListener;
    }

    public interface ProductListener{
        void onProductClick(Product product);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemBestSellerProductBinding binding = ItemBestSellerProductBinding.inflate(inflater, parent, false);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.bind(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        private ItemBestSellerProductBinding binding;

        public ProductViewHolder(ItemBestSellerProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Product product){
            binding.setProduct(product);
            itemView.setOnClickListener((View v) -> {productListener.onProductClick(product);});
            binding.executePendingBindings();
        }
    }
}
