package com.mahavira.partnersms.inventory.presentation.requestdetail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mahavira.partnersms.inventory.databinding.ItemRequestCheckListBinding;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by norman on 09/08/18.
 */

public class ReturnRequestChecklistAdapter extends RecyclerView.Adapter<ReturnRequestChecklistAdapter.ChecklistViewHolder> {

    private ArrayList mData = new ArrayList();

    private Context mContext;

    ReturnRequestChecklistAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ChecklistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);

        ItemRequestCheckListBinding binding = ItemRequestCheckListBinding.inflate(inflater, parent, false);

        return new ChecklistViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ChecklistViewHolder holder, int position) {
        Map.Entry<String, Boolean> map = (Map.Entry) mData.get(position);
        holder.bind(map.getKey(), map.getValue());
    }

    void replaceData(Map<String, Boolean> componentMap) {
        mData.addAll(componentMap.entrySet());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ChecklistViewHolder extends RecyclerView.ViewHolder {

        ItemRequestCheckListBinding mBinding;

        public ChecklistViewHolder(ItemRequestCheckListBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(String component, Boolean checked) {
            mBinding.setComponent(component);
            mBinding.setChecked(checked);
            mBinding.executePendingBindings();
        }
    }
}
