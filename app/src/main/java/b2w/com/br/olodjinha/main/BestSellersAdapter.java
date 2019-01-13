package b2w.com.br.olodjinha.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import b2w.com.br.olodjinha.R;
import b2w.com.br.olodjinha.data.models.ProductDTO;
import b2w.com.br.olodjinha.listener.OnItemSelected;
import b2w.com.br.olodjinha.util.CurrencyUtil;
import b2w.com.br.olodjinha.util.GlideUtil;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BestSellersAdapter extends RecyclerView.Adapter {
    private final List<ProductDTO> mProducts;
    private final Context mContext;
    private final OnItemSelected mListener;

    public BestSellersAdapter(Context context, List<ProductDTO> data, OnItemSelected onItemSelected) {
        mProducts = data;
        mContext = context;
        mListener = onItemSelected;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.product_item, viewGroup, false);

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final ViewHolder holder = (ViewHolder) viewHolder;
        final int adapterPosition = holder.getAdapterPosition();

        GlideUtil.setBannerImage(mContext, holder.getProductImage(),
                mProducts.get(adapterPosition).getUrl());

        holder.getProductDescription().setText(mProducts.get(adapterPosition).getName());

        SpannableString spannableString = new SpannableString(CurrencyUtil.formatBigDecimalToCurrency(mProducts.get(adapterPosition).getPrice()));
        spannableString.setSpan(new StrikethroughSpan(), 0, spannableString.length(), 0);
        holder.getPrice().setText(spannableString);

        holder.getPriceWithDiscount().setText(CurrencyUtil.formatBigDecimalToCurrency(mProducts.get(adapterPosition).getPriceWithDiscount()));

        holder.getContainer().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemSelected(mProducts.get(adapterPosition));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.container)
        View mContainer;

        @BindView(R.id.imageView_product)
        ImageView mProductImage;

        @BindView(R.id.textview_description)
        TextView mProductDescription;

        @BindView(R.id.textview_price)
        TextView mPrice;

        @BindView(R.id.textview_price_with_discount)
        TextView mPriceWithDiscount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public ImageView getProductImage() {
            return mProductImage;
        }

        public TextView getProductDescription() {
            return mProductDescription;
        }

        public TextView getPrice() {
            return mPrice;
        }

        public TextView getPriceWithDiscount() {
            return mPriceWithDiscount;
        }

        public View getContainer() {
            return mContainer;
        }
    }
}
