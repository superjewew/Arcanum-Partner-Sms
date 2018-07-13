package com.mahavira.partnersms;

import android.os.Bundle;

import com.mahavira.partnersms.databinding.ActivityMainBinding;
import com.mahavira.partnersms.login.LoginRouter;

import javax.inject.Inject;

import presentation.BaseActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    @Inject
    LoginRouter mLoginRouter;

    @Override
    public int getViewModelBindingVariable() {
        return NO_VIEW_MODEL_BINDING_VARIABLE;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoginRouter.goToLogin(this);
    }
}
