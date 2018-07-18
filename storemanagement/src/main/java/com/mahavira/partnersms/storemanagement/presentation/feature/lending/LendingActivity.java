package com.mahavira.partnersms.storemanagement.presentation.feature.lending;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.mahavira.partnersms.base.presentation.BaseActivity;
import com.mahavira.partnersms.base.presentation.ExtraInjectable;
import com.mahavira.partnersms.inventory.BR;
import com.mahavira.partnersms.inventory.domain.entity.Boardgame;
import com.mahavira.partnersms.storemanagement.R;
import com.mahavira.partnersms.storemanagement.databinding.ActivityLendingBinding;
import com.mahavira.partnersms.storemanagement.domain.entitiy.Partner;
import com.mahavira.partnersms.storemanagement.domain.entitiy.ProductSelected;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class LendingActivity extends BaseActivity<ActivityLendingBinding, LendingViewModel>
        implements ExtraInjectable {

    public static final String PARTNER_EXTRA = "partner";

    private LendingAdapter mAdapter;

    private List<ProductSelected> mAvailableProducts;

    private Partner mPartner;

    @Override
    public int getViewModelBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_lending;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupAdapter();
        setupRecylerView(mAdapter);

        getViewModel().getProductData().observe(this, products -> {
            if (products != null) {
                switch (products.status) {
                    case SUCCESS:
                        mAvailableProducts = products.data;
                        mAdapter.replaceData(products.data);
                        break;
                    case ERROR:
                        Toast.makeText(this, "Error retrieving products, " + products.message, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        getViewModel().attemptGetProducts();
    }

    private void setupRecylerView(LendingAdapter mAdapter) {
        getDataBinding().availableProductsRv.setLayoutManager(new LinearLayoutManager(this));
        getDataBinding().availableProductsRv.setAdapter(mAdapter);
    }

    private void setupAdapter() {
        mAdapter = new LendingAdapter(this);
    }

    @Override
    public void injectExtras(@NonNull Bundle extras) {
        if(extras.containsKey(PARTNER_EXTRA)) {
            mPartner = Parcels.unwrap(extras.getParcelable(PARTNER_EXTRA));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.lending_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_lent) {
            getViewModel().attemptLentProducts(mAvailableProducts);
        }
        return super.onOptionsItemSelected(item);
    }
}
