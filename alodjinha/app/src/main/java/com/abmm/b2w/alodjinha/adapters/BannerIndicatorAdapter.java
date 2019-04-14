package com.abmm.b2w.alodjinha.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abmm.b2w.alodjinha.R;
import com.abmm.b2w.alodjinha.activities.main.IMainPresenter;
import com.abmm.b2w.alodjinha.model.Banner;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BannerIndicatorAdapter extends RecyclerView.Adapter<BannerIndicatorAdapter.BannerIndicatorViewHolder> {

    private IMainPresenter ctx;

    public BannerIndicatorAdapter(IMainPresenter context) {
        this.ctx = context;
    }

    @NonNull
    @Override
    public BannerIndicatorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_banner_indicator, parent, false);
        return new BannerIndicatorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerIndicatorViewHolder holder, int position) {
        final Banner element = ctx.getBannersList().get(position);

        holder.bind(element);
    }

    @Override
    public int getItemCount() {
        return ctx.getBannersList().size();
    }

    class BannerIndicatorViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.banner_indicator) View indicator;

        BannerIndicatorViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bind(final Banner banner) {
            int resId = R.drawable.shape_banner_indicator;
            if (banner.isActive()) {
                resId = R.drawable.shape_banner_indicator_active;
            }
            indicator.setBackgroundResource(resId);

            indicator.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ctx.getCurrentBannerPosition() != getLayoutPosition()) {
                        notifyItemChanged(ctx.getCurrentBannerPosition());
                        ctx.setCurrentBannerPosition(getLayoutPosition());
                        ctx.updateData(banner);
                    }
                }
            });
        }
    }

}
