package com.mahavira.partnersms.storemanagement.presentation.feature.partnerdetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.mahavira.partnersms.base.presentation.BaseActivity;
import com.mahavira.partnersms.base.presentation.ExtraInjectable;
import com.mahavira.partnersms.storemanagement.BR;
import com.mahavira.partnersms.storemanagement.R;
import com.mahavira.partnersms.storemanagement.databinding.ActivityPartnerDetailBinding;
import com.mahavira.partnersms.storemanagement.domain.entitiy.Partner;

import org.parceler.Parcels;

public class PartnerDetailActivity extends BaseActivity<ActivityPartnerDetailBinding, PartnerDetailViewModel>
        implements ExtraInjectable{

    public static final String PARTNER_EXTRA = "partner";

    private Partner mPartner;

    @Override
    public int getViewModelBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_partner_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getDataBinding().setPartner(mPartner);

        getViewModel().getDeletePartnerResult().observe(this, deleteResult -> {
            if(deleteResult != null) {
                switch (deleteResult.status) {
                    case SUCCESS:
                        Toast.makeText(this, "Delete Successful", Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                    case ERROR:
                        Toast.makeText(this, "Delete Failed, " + deleteResult.message, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    @Override
    public void injectExtras(@NonNull Bundle extras) {
        if(extras.containsKey(PARTNER_EXTRA)) {
            mPartner = Parcels.unwrap(extras.getParcelable(PARTNER_EXTRA));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.partner_detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_delete) {
            getViewModel().attemptDelete(mPartner);
        }
        return super.onOptionsItemSelected(item);
    }
}
