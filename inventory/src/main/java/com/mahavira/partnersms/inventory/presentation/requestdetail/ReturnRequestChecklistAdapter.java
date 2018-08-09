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

//    private Map<String, Boolean> mComponentMap;

    private ArrayList mData = new ArrayList();

    private Context mContext;

    ReturnRequestChecklistAdapter(Context context, Map<String, Boolean> componentMap) {
        mContext = context;
        mData.addAll(componentMap.entrySet());
//        mComponentMap = componentMap;
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
//        String component = mComponentMap.keySet().toArray()[position].toString();
        Map.Entry<String, Boolean> map = (Map.Entry) mData.get(position);
        holder.bind(map.getKey(), map.getValue());
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
