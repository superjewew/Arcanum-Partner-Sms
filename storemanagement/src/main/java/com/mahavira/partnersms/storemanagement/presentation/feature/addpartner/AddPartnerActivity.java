package com.mahavira.partnersms.storemanagement.presentation.feature.addpartner;

import android.os.Bundle;

import com.mahavira.partnersms.base.presentation.BaseActivity;
import com.mahavira.partnersms.storemanagement.R;
import com.mahavira.partnersms.storemanagement.databinding.ActivityAddPartnerBinding;

public class AddPartnerActivity extends BaseActivity<ActivityAddPartnerBinding, AddPartnerViewModel> {

    @Override
    public int getViewModelBindingVariable() {
        return NO_VIEW_MODEL_BINDING_VARIABLE;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_partner;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_partner);
    }
}
