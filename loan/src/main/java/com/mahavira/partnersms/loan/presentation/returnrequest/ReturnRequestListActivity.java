package com.mahavira.partnersms.loan.presentation.returnrequest;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.mahavira.partnersms.base.presentation.BaseActivity;
import com.mahavira.partnersms.loan.BR;
import com.mahavira.partnersms.loan.R;
import com.mahavira.partnersms.loan.databinding.ActivityReturnRequestListBinding;
import com.mahavira.partnersms.loan.presentation.LoanRouter;

import javax.inject.Inject;

public class ReturnRequestListActivity extends BaseActivity<ActivityReturnRequestListBinding, ReturnRequestViewModel> {

    private ReturnRequestAdapter mAdapter;

    @Inject
    LoanRouter mRouter;

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

        getViewModel().getReturnRequestsData().observe(this, listResource -> {
            if(listResource != null) {
                switch (listResource.status) {
                    case SUCCESS:
                        mAdapter.replaceData(listResource.data);
                        break;
                    case ERROR:
                        Toast.makeText(this, listResource.message, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        getViewModel().getRequestClicked().observe(this, request -> {
            if(request != null) {
                mRouter.goToReturnRequestDetail(this, request);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        getViewModel().attemptGetReturnRequest();
    }

    private void setupRecyclerView(ReturnRequestAdapter mAdapter) {
        getDataBinding().requestList.setLayoutManager(new LinearLayoutManager(this));
        getDataBinding().requestList.setAdapter(mAdapter);
    }

    private void setupAdapter() {
        mAdapter = new ReturnRequestAdapter(this, getViewModel());
    }
}
