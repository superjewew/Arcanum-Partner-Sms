package com.mahavira.partnersms.inventory.presentation;

import android.content.Context;

import com.mahavira.partnersms.inventory.domain.entity.ReturnRequest;

/**
 * Created by norman on 17/07/18.
 *
 */

public interface InventoryRouter {
    void goToAddProduct(Context context);
    void goToProductList(Context context);
    void goToReturnRequestList(Context context);
    void goToReturnRequestDetail(Context context, ReturnRequest request);
}
