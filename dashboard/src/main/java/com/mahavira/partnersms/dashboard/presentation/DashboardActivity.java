package com.mahavira.partnersms.dashboard.presentation;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.mahavira.partnersms.base.presentation.BaseActivity;
import com.mahavira.partnersms.dashboard.R;
import com.mahavira.partnersms.dashboard.databinding.ActivityDashboardBinding;
import com.mahavira.partnersms.inventory.presentation.InventoryRouter;
import com.mahavira.partnersms.storemanagement.presentation.StoreManagementRouter;

import javax.inject.Inject;

public class DashboardActivity extends BaseActivity<ActivityDashboardBinding, DashboardViewModel> {

    @Inject
    StoreManagementRouter mRouter;

    @Inject
    InventoryRouter mInventoryRouter;

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

        getDataBinding().partnerListBtn.setOnClickListener(view -> mRouter.goToPartnerList(this));

        getDataBinding().addProductBtn.setOnClickListener(view -> mInventoryRouter.goToAddProduct(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.action_add_partner) {
            mRouter.goToAddPartner(this);
        }
        return super.onOptionsItemSelected(item);
    }
}
