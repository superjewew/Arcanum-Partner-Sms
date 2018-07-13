package com.mahavira.partnersms.dashboard.presentation;

import android.os.Bundle;

import com.mahavira.partnersms.base.presentation.BaseActivity;
import com.mahavira.partnersms.dashboard.R;
import com.mahavira.partnersms.dashboard.databinding.ActivityDashboardBinding;

public class DashboardActivity extends BaseActivity<ActivityDashboardBinding, DashboardViewModel> {

    @Override
    public int getViewModelBindingVariable() {
        return NO_VIEW_MODEL_BINDING_VARIABLE;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_dashboard;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
