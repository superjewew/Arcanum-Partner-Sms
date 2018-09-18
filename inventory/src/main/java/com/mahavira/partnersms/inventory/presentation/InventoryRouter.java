package com.mahavira.partnersms.inventory.presentation;

import android.content.Context;
import com.mahavira.partnersms.base.entity.Boardgame;

/**
 * Created by norman on 17/07/18.
 *
 */

public interface InventoryRouter {
    void goToAddProduct(Context context);
    void goToEditProduct(Context context, Boardgame product);
    void goToProductList(Context context);
}
