package com.mahavira.partnersms.inventory.presentation.returnrequest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mahavira.partnersms.inventory.databinding.ItemRequestListBinding;
import com.mahavira.partnersms.inventory.domain.entity.ReturnRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by norman on 07/08/18.
 */

public class ReturnRequestAdapter extends RecyclerView.Adapter<ReturnRequestAdapter.ReturnRequestViewHolder> {

    private List<ReturnRequest> mRequests = new ArrayList<>();

    private Context mContext;

    ReturnRequestAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ReturnRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);

        ItemRequestListBinding binding = ItemRequestListBinding.inflate(inflater, parent, false);

        return new ReturnRequestViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReturnRequestViewHolder holder, int position) {
        ReturnRequest request = mRequests.get(position);
        holder.bind(request);
    }

    @Override
    public int getItemCount() {
        return mRequests.size();
    }

    void replaceData(List<ReturnRequest> requests) {
        mRequests = requests;
        notifyDataSetChanged();
    }

    class ReturnRequestViewHolder extends RecyclerView.ViewHolder {

        ItemRequestListBinding mBinding;

        public ReturnRequestViewHolder(ItemRequestListBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(ReturnRequest request) {
            mBinding.setRequest(request);
            mBinding.executePendingBindings();
        }
    }
}
