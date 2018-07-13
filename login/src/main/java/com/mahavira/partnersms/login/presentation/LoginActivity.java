package com.mahavira.partnersms.login.presentation;

import android.os.Bundle;

import com.mahavira.partnersms.login.R;
import com.mahavira.partnersms.login.databinding.ActivityLoginBinding;

import presentation.BaseActivity;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {

    @Override
    public int getViewModelBindingVariable() {
        return NO_VIEW_MODEL_BINDING_VARIABLE;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
