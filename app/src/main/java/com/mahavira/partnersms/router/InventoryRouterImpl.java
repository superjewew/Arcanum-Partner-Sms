package com.mahavira.partnersms.router;

import android.content.Context;
import android.content.Intent;

import com.mahavira.partnersms.inventory.presentation.InventoryRouter;
import com.mahavira.partnersms.inventory.presentation.addproduct.AddProductActivity;
import com.mahavira.partnersms.inventory.presentation.getproducts.ProductListActivity;

/**
 * Created by norman on 17/07/18.
 *
 */

public class InventoryRouterImpl implements InventoryRouter {
    @Override
    public void goToAddProduct(Context context) {
        Intent intent = new Intent(context, AddProductActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void goToProductList(Context context) {
        Intent intent = new Intent(context, ProductListActivity.class);
        context.startActivity(intent);
    }
}
