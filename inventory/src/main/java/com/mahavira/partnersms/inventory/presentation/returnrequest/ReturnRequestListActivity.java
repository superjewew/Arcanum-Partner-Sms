package com.mahavira.partnersms.inventory.presentation.returnrequest;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.mahavira.partnersms.base.presentation.BaseActivity;
import com.mahavira.partnersms.inventory.BR;
import com.mahavira.partnersms.inventory.R;
import com.mahavira.partnersms.inventory.databinding.ActivityReturnRequestListBinding;

public class ReturnRequestListActivity extends BaseActivity<ActivityReturnRequestListBinding, ReturnRequestViewModel> {

    private ReturnRequestAdapter mAdapter;

    @Override
    public int getViewModelBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_return_request_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupAdapter();
        setupRecyclerView(mAdapter);
    }

    private void setupRecyclerView(ReturnRequestAdapter mAdapter) {
        getDataBinding().requestList.setLayoutManager(new LinearLayoutManager(this));
        getDataBinding().requestList.setAdapter(mAdapter);
    }

    private void setupAdapter() {
        mAdapter = new ReturnRequestAdapter(this);
    }
}
