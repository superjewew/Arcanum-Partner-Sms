package com.mahavira.partnersms.storemanagement.presentation.feature.lending;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mahavira.partnersms.storemanagement.databinding.ItemAvailableProductLendingListBinding;
import com.mahavira.partnersms.storemanagement.domain.entitiy.ProductSelected;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by norman on 18/07/18.
 *
 */

public class LendingAdapter extends RecyclerView.Adapter<LendingAdapter.LendingViewHolder> {

    private Context mContext;

    private List<ProductSelected> mAvailableProduct = new ArrayList<>();

    public LendingAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public LendingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);

        ItemAvailableProductLendingListBinding binding = ItemAvailableProductLendingListBinding.inflate(inflater, parent, false);

        return new LendingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LendingViewHolder holder, int position) {
        ProductSelected product = mAvailableProduct.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return mAvailableProduct.size();
    }

    public void replaceData(List<ProductSelected> products) {
        mAvailableProduct = products;
        notifyDataSetChanged();
    }

    class LendingViewHolder extends RecyclerView.ViewHolder {

        private ItemAvailableProductLendingListBinding mBinding;

        LendingViewHolder(ItemAvailableProductLendingListBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(ProductSelected product) {
            mBinding.setProductItem(product);
            mBinding.executePendingBindings();
        }
    }
}
