package com.mahavira.partnersms.storemanagement.presentation.feature.partnerlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mahavira.partnersms.storemanagement.databinding.ItemPartnerListBinding;
import com.mahavira.partnersms.storemanagement.domain.entitiy.Partner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by norman on 16/07/18.
 *
 */

public class PartnerListAdapter extends RecyclerView.Adapter<PartnerListAdapter.PartnerListViewHolder> {

    private PartnerListViewModel mViewModel;

    private List<Partner> mPartnerList = new ArrayList<>();

    private Context mContext;

    PartnerListAdapter(Context context, PartnerListViewModel viewModel, List<Partner> partners) {
        mViewModel = viewModel;
        mPartnerList = partners;
        mContext = context;
    }

    @NonNull
    @Override
    public PartnerListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);

        ItemPartnerListBinding binding = ItemPartnerListBinding.inflate(inflater, parent, false);

        return new PartnerListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PartnerListViewHolder holder, int position) {
        Partner partner = mPartnerList.get(position);

        holder.bind(partner);
    }

    @Override
    public int getItemCount() {
        return mPartnerList.size();
    }

    void replaceData(List<Partner> partners) {
        mPartnerList = partners;
        notifyDataSetChanged();
    }

    class PartnerListViewHolder extends RecyclerView.ViewHolder {

        ItemPartnerListBinding mBinding;

        PartnerListViewHolder(ItemPartnerListBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(Partner partner) {
            ItemClickListener clickListener = partner1 -> mViewModel.getPartnerClicked().setValue(partner1);
            mBinding.setPartner(partner);
            mBinding.setClickListener(clickListener);
        }
    }

    public interface ItemClickListener {

        void onItemClicked(Partner partner);

    }
}
