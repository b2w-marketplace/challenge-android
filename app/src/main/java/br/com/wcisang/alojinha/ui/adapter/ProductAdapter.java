package br.com.wcisang.alojinha.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.wcisang.alojinha.R;
import br.com.wcisang.alojinha.databinding.ItemBestSellerProductBinding;
import br.com.wcisang.alojinha.model.Product;

/**
 * Created by WCisang on 07/06/2018.
 */
public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;
    List<Product> products;
    private ProductListener productListener;
    private boolean show_footer = false;


    public ProductAdapter(List<Product> products, ProductListener productListener) {
        this.products = products;
        this.productListener = productListener;
    }

    public void addToList(List<Product> products) {
        if (products.size() == 0) show_footer = false;
        else show_footer = true;
        this.products.addAll(products);
        notifyDataSetChanged();
    }

    public interface ProductListener{
        void onProductClick(Product product);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            ItemBestSellerProductBinding binding = ItemBestSellerProductBinding.inflate(inflater, parent, false);
            return new ProductViewHolder(binding);
        }
        else if (viewType == TYPE_FOOTER){
            View loadingView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_loading,
                    parent, false);
            return new ProductFooterViewHolder(loadingView);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ProductViewHolder)
            ((ProductViewHolder)holder).bind(products.get(position));
    }

    @Override
    public int getItemCount() {
        if (products.size() == 0 || !show_footer)
            return products.size();
        return products.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionFooter(position)) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    private boolean isPositionFooter(int position) {
        return position == products.size();
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

    public class ProductFooterViewHolder extends RecyclerView.ViewHolder {

        public ProductFooterViewHolder(View view) {
            super(view);
        }
    }
}
