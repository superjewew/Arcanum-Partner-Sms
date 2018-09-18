package com.mahavira.partnersms.router;

import static com.mahavira.partnersms.inventory.presentation.addproduct.AddProductActivity.PRODUCT_EXTRA;

import android.content.Context;
import android.content.Intent;

import com.mahavira.partnersms.base.entity.Boardgame;
import com.mahavira.partnersms.inventory.presentation.InventoryRouter;
import com.mahavira.partnersms.inventory.presentation.addproduct.AddProductActivity;
import com.mahavira.partnersms.inventory.presentation.getproducts.ProductListActivity;
import org.parceler.Parcels;

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
    public void goToEditProduct(final Context context, final Boardgame product) {
        Intent intent = new Intent(context, AddProductActivity.class);
        intent.putExtra(PRODUCT_EXTRA, Parcels.wrap(product));
        context.startActivity(intent);
    }

    @Override
    public void goToProductList(Context context) {
        Intent intent = new Intent(context, ProductListActivity.class);
        context.startActivity(intent);
    }
}
