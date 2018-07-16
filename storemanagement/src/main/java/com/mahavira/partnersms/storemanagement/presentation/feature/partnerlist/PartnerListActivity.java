package com.mahavira.partnersms.storemanagement.presentation.feature.partnerlist;

import android.os.Bundle;

import com.mahavira.partnersms.base.presentation.BaseActivity;
import com.mahavira.partnersms.storemanagement.R;
import com.mahavira.partnersms.storemanagement.databinding.ActivityPartnerListBinding;

public class PartnerListActivity extends BaseActivity<ActivityPartnerListBinding, PartnerListViewModel> {

    @Override
    public int getViewModelBindingVariable() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_partner_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
