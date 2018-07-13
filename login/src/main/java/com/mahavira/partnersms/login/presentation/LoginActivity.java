package com.mahavira.partnersms.login.presentation;

import android.os.Bundle;
import android.widget.Toast;

import com.mahavira.partnersms.login.R;
import com.mahavira.partnersms.login.databinding.ActivityLoginBinding;

import com.mahavira.partnersms.base.presentation.BaseActivity;

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

        getViewModel().getLoginResponse().observe(this, authResultResource -> {
            if (authResultResource != null) {
                switch (authResultResource.status) {
                    case SUCCESS:
                        Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
                        break;
                    case ERROR:
                        Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        getViewModel().attemptLogin("admin@arcanum.com", "arcanum1234");
    }
}
