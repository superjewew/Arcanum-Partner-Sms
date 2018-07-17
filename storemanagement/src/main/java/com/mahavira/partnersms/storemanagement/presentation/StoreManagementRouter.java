package com.mahavira.partnersms.storemanagement.presentation;

import android.content.Context;

import com.mahavira.partnersms.storemanagement.domain.entitiy.Partner;

/**
 * Created by norman on 14/07/18.
 *
 */

public interface StoreManagementRouter {
    void goToAddPartner(Context context);
    void goToPartnerList(Context context);
    void goToPartnerDetail(Context context, Partner partner);
}
