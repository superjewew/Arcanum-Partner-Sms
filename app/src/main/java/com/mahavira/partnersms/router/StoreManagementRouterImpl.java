package com.mahavira.partnersms.router;

import android.content.Context;
import android.content.Intent;

import com.mahavira.partnersms.storemanagement.presentation.StoreManagementRouter;
import com.mahavira.partnersms.storemanagement.presentation.feature.addpartner.AddPartnerActivity;

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
}
