package com.mahavira.partnersms.inventory.presentation;

import android.os.Bundle;

import com.mahavira.partnersms.base.presentation.BaseActivity;
import com.mahavira.partnersms.inventory.R;
import com.mahavira.partnersms.inventory.databinding.ActivityAddProductBinding;

public class AddProductActivity extends BaseActivity<ActivityAddProductBinding, AddProductViewModel> {

    @Override
    public int getViewModelBindingVariable() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_product;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
    }
}
