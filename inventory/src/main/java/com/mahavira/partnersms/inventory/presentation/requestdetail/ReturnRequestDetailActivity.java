package com.mahavira.partnersms.inventory.presentation.requestdetail;

import android.os.Bundle;

import com.mahavira.partnersms.base.presentation.BaseActivity;
import com.mahavira.partnersms.inventory.BR;
import com.mahavira.partnersms.inventory.R;
import com.mahavira.partnersms.inventory.databinding.ActivityReturnRequestDetailBinding;

public class ReturnRequestDetailActivity extends BaseActivity<ActivityReturnRequestDetailBinding, ReturnRequestDetailViewModel> {

    @Override
    public int getViewModelBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_return_request_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
