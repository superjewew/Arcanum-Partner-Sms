package com.mahavira.partnersms.loan.presentation.requestdetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.mahavira.partnersms.base.presentation.BaseActivity;
import com.mahavira.partnersms.base.presentation.ExtraInjectable;
import com.mahavira.partnersms.base.entity.ReturnRequest;
import com.mahavira.partnersms.loan.BR;
import com.mahavira.partnersms.loan.R;
import com.mahavira.partnersms.loan.databinding.ActivityReturnRequestDetailBinding;

import org.parceler.Parcels;

public class ReturnRequestDetailActivity extends BaseActivity<ActivityReturnRequestDetailBinding, ReturnRequestDetailViewModel>
        implements ExtraInjectable {

    public static final String REQUEST_EXTRA = "request";

    private ReturnRequestChecklistAdapter mAdapter;

    private ReturnRequest mRequest;

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

        getDataBinding().setRequest(mRequest);

        setupAdapter();
        setupRecyclerView(mAdapter);

        getViewModel().getApproveClickedEvent().observe(this, approveClicked -> {
            getViewModel().attemptApprove(mRequest);
        });

        getViewModel().getApproveData().observe(this, approveSuccess -> {
            if(approveSuccess != null) {
                switch (approveSuccess.status) {
                    case SUCCESS:
                        Toast.makeText(this, "Request Approved", Toast.LENGTH_SHORT).show();
                        this.finish();
                        break;
                    case ERROR:
                        Toast.makeText(this, approveSuccess.message, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    private void setupAdapter() {
        mAdapter = new ReturnRequestChecklistAdapter(this);
        mAdapter.replaceData(mRequest.getChecklist());
    }

    private void setupRecyclerView(ReturnRequestChecklistAdapter mAdapter) {
        getDataBinding().componentList.setLayoutManager(new LinearLayoutManager(this));
        getDataBinding().componentList.setAdapter(mAdapter);
    }

    @Override
    public void injectExtras(@NonNull Bundle extras) {
        if(extras.containsKey(REQUEST_EXTRA)) {
            mRequest = Parcels.unwrap(extras.getParcelable(REQUEST_EXTRA));
        }
    }
}
