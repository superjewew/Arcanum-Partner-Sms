package com.mahavira.partnersms.router;

import android.content.Context;
import android.content.Intent;

import com.mahavira.partnersms.storemanagement.domain.entitiy.Partner;
import com.mahavira.partnersms.storemanagement.presentation.StoreManagementRouter;
import com.mahavira.partnersms.storemanagement.presentation.feature.addpartner.AddPartnerActivity;
import com.mahavira.partnersms.storemanagement.presentation.feature.lending.LendingActivity;
import com.mahavira.partnersms.storemanagement.presentation.feature.partnerdetail.PartnerDetailActivity;
import com.mahavira.partnersms.storemanagement.presentation.feature.partnerlist.PartnerListActivity;

import org.parceler.Parcels;

import static com.mahavira.partnersms.storemanagement.presentation.feature.partnerdetail.PartnerDetailActivity.PARTNER_EXTRA;

/**
 * Created by norman on 14/07/18.
 *
 */

public class StoreManagementRouterImpl implements StoreManagementRouter {
    @Override
    public void goToAddPartner(Context context) {
        Intent intent = new Intent(context, AddPartnerActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void goToPartnerList(Context context) {
        Intent intent = new Intent(context, PartnerListActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void goToPartnerDetail(Context context, Partner partner) {
        Intent intent = new Intent(context, PartnerDetailActivity.class);
        intent.putExtra(PARTNER_EXTRA, Parcels.wrap(partner));
        context.startActivity(intent);
    }

    @Override
    public void goToLending(Context context, Partner partner) {
        Intent intent = new Intent(context, LendingActivity.class);
        intent.putExtra(PARTNER_EXTRA, Parcels.wrap(partner));
        context.startActivity(intent);
    }
}
