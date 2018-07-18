package com.mahavira.partnersms.inventory.presentation.getproducts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mahavira.partnersms.inventory.databinding.ItemProductListBinding;
import com.mahavira.partnersms.inventory.domain.entity.Boardgame;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by norman on 18/07/18.
 *
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder> {

    private List<Boardgame> mProductList = new ArrayList<>();

    private Context mContext;

    ProductListAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);

        ItemProductListBinding binding = ItemProductListBinding.inflate(inflater, parent, false);

        return new ProductListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListViewHolder holder, int position) {
        Boardgame product = mProductList.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    void replaceData(List<Boardgame> products) {
        mProductList = products;
        notifyDataSetChanged();
    }

    class ProductListViewHolder extends RecyclerView.ViewHolder {

        ItemProductListBinding mBinding;

        ProductListViewHolder(ItemProductListBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(Boardgame product) {
            mBinding.setProduct(product);
            mBinding.executePendingBindings();
        }
    }
}
