package com.mahavira.partnersms.login.presentation;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.widget.Toast;

import com.mahavira.partnersms.dashboard.presentation.DashboardRouter;
import com.mahavira.partnersms.login.BR;
import com.mahavira.partnersms.login.R;
import com.mahavira.partnersms.login.databinding.ActivityLoginBinding;

import com.mahavira.partnersms.base.presentation.BaseActivity;
import com.mahavira.partnersms.login.domain.entity.AuthParam;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {

    @Inject
    DashboardRouter mRouter;

    @Override
    public int getViewModelBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowHomeEnabled(false);
        }

        getViewModel().getLoginResponse().observe(this, authResultResource -> {
            if (authResultResource != null) {
                switch (authResultResource.status) {
                    case SUCCESS:
                        Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
                        Log.d("LOGIN", "login success");
                        mRouter.goToDashboard(this);
                        break;
                    case ERROR:
                        Toast.makeText(this, "Login Failed, " + authResultResource.message, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        getDataBinding().setParam(new AuthParam("", ""));
    }
}
