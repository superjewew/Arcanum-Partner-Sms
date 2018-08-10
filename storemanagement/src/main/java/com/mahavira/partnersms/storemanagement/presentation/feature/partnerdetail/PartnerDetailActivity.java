package com.mahavira.partnersms.storemanagement.presentation.feature.partnerdetail;

import android.databinding.BindingAdapter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mahavira.partnersms.base.presentation.BaseActivity;
import com.mahavira.partnersms.base.presentation.ExtraInjectable;
import com.mahavira.partnersms.storemanagement.BR;
import com.mahavira.partnersms.storemanagement.R;
import com.mahavira.partnersms.storemanagement.databinding.ActivityPartnerDetailBinding;
import com.mahavira.partnersms.storemanagement.domain.entitiy.Partner;
import com.mahavira.partnersms.storemanagement.presentation.StoreManagementRouter;

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

public class PartnerDetailActivity extends BaseActivity<ActivityPartnerDetailBinding, PartnerDetailViewModel>
        implements ExtraInjectable{

    public static final String PARTNER_EXTRA = "partner";

    private Partner mPartner;

    @Inject
    StoreManagementRouter mRouter;

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
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(mPartner.getBorrowedGames().size() > 0) {
            menu.findItem(R.id.action_lent).setEnabled(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_delete) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Deleting " + mPartner.getName());
            alertDialogBuilder.setMessage("Are you sure?");
            alertDialogBuilder.setPositiveButton("OK", (dialog, which) -> getViewModel().attemptDelete(mPartner));
            alertDialogBuilder.setNegativeButton("Cancel", (dialog, which) -> {
                if (dialog != null) {
                    dialog.dismiss();
                }
            });

            alertDialogBuilder.create().show();

        } else if(id == R.id.action_lent) {
            mRouter.goToLending(this, mPartner);
        }
        return super.onOptionsItemSelected(item);
    }

    @BindingAdapter("borrowedList")
    public static void setBorrowedList(LinearLayout view, List<String> list) {
        if(list != null) {
            for (String product : list) {
                TextView tv = new TextView(view.getContext());
                tv.setText(product);
                view.addView(tv);
            }
        }
    }
}
