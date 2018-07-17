package com.mahavira.partnersms.storemanagement.presentation.feature.partnerdetail;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.mahavira.partnersms.base.presentation.BaseActivity;
import com.mahavira.partnersms.base.presentation.ExtraInjectable;
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
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_partner_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getDataBinding().setPartner(mPartner);
    }

    @Override
    public void injectExtras(@NonNull Bundle extras) {
        if(extras.containsKey(PARTNER_EXTRA)) {
            mPartner = Parcels.unwrap(extras.getParcelable(PARTNER_EXTRA));
        }
    }
}
