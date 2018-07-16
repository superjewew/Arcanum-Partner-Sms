package com.mahavira.partnersms.storemanagement.presentation.feature.partnerlist;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.mahavira.partnersms.base.presentation.BaseActivity;
import com.mahavira.partnersms.storemanagement.BR;
import com.mahavira.partnersms.storemanagement.R;
import com.mahavira.partnersms.storemanagement.databinding.ActivityPartnerListBinding;
import com.mahavira.partnersms.storemanagement.domain.entitiy.Partner;

import java.util.ArrayList;
import java.util.List;

public class PartnerListActivity extends BaseActivity<ActivityPartnerListBinding, PartnerListViewModel> {

    private PartnerListAdapter mAdapter;

    private List<Partner> mPartnerList = new ArrayList<>();

    @Override
    public int getViewModelBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_partner_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupAdapter();
        setupRecyclerView(mAdapter);

        getViewModel().getPartnersData().observe(this, partners -> {
            if (partners != null) {
                switch (partners.status) {
                    case SUCCESS:
                        mAdapter.replaceData(partners.data);
                        break;
                }
            }
        });

        getViewModel().getErrorMessage().observe(this,
                errorMessage -> Toast.makeText(this, "Failed fetch partner list, "
                        + errorMessage, Toast.LENGTH_SHORT).show());
    }

    private void setupAdapter() {
        mAdapter = new PartnerListAdapter(this, getViewModel(), mPartnerList);
    }

    private void setupRecyclerView(PartnerListAdapter mAdapter) {
        getDataBinding().partnersRv.setLayoutManager(new LinearLayoutManager(this));
        getDataBinding().partnersRv.setAdapter(mAdapter);
    }
}
