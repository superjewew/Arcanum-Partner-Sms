package com.mahavira.partnersms.storemanagement.presentation.feature.addpartner;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;
import com.mahavira.partnersms.base.presentation.BaseActivity;
import com.mahavira.partnersms.storemanagement.R;
import com.mahavira.partnersms.storemanagement.databinding.ActivityAddPartnerBinding;
import com.mahavira.partnersms.storemanagement.domain.entitiy.Partner;

public class AddPartnerActivity extends BaseActivity<ActivityAddPartnerBinding, AddPartnerViewModel> {

    @Override
    public int getViewModelBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_partner;
    }

    private Partner mPartner = new Partner();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        observeAddPartnerResult();
        observeErrorMessage();

        getDataBinding().setPartner(mPartner);
    }

    private void observeAddPartnerResult() {
        getViewModel().getAddPartnerResult().observe(this, addPartner -> {
            if (addPartner != null) {
                switch (addPartner.status) {
                    case SUCCESS:
                        Toast.makeText(this, "Add Partner Success", Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                }
            }
        });
    }

    private void observeErrorMessage() {
        getViewModel().getErrorMessage().observe(this, errorMessage -> {
            if (errorMessage != null) {
                Toast.makeText(this, "Add Partner Failed, " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_partner_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_save) {
            getViewModel().attemptAddPartner(mPartner);
        }
        return super.onOptionsItemSelected(item);
    }
}
