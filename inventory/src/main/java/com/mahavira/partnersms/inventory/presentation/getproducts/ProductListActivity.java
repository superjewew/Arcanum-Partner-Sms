package com.mahavira.partnersms.inventory.presentation.getproducts;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.mahavira.partnersms.base.presentation.BaseActivity;
import com.mahavira.partnersms.inventory.BR;
import com.mahavira.partnersms.inventory.R;
import com.mahavira.partnersms.inventory.databinding.ActivityProductListBinding;
import com.mahavira.partnersms.inventory.domain.entity.Boardgame;

import java.util.List;

public class ProductListActivity extends BaseActivity<ActivityProductListBinding, ProductListViewModel> {

    private ProductListAdapter mAdapter;

    @Override
    public int getViewModelBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_product_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupAdapter();
        setupRecyclerView(mAdapter);

        getViewModel().getProductListData().observe(this, productList -> {
            if(productList != null) {
                switch (productList.status) {
                    case SUCCESS:
                        mAdapter.replaceData(productList.data);
                        break;
                    case ERROR:
                        Toast.makeText(this, "Failed to retrieve boardgame list, " + productList.message, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        getViewModel().attemptGetProducts();
    }

    private void setupRecyclerView(ProductListAdapter adapter) {
        getDataBinding().productListRv.setLayoutManager(new LinearLayoutManager(this));
        getDataBinding().productListRv.setAdapter(adapter);
    }

    private void setupAdapter() {
        mAdapter = new ProductListAdapter(this);
    }
}
