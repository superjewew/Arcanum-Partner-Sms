package com.mahavira.partnersms.router;

import android.content.Context;
import android.content.Intent;

import com.mahavira.partnersms.inventory.domain.entity.ReturnRequest;
import com.mahavira.partnersms.inventory.presentation.InventoryRouter;
import com.mahavira.partnersms.inventory.presentation.addproduct.AddProductActivity;
import com.mahavira.partnersms.inventory.presentation.getproducts.ProductListActivity;
import com.mahavira.partnersms.inventory.presentation.requestdetail.ReturnRequestDetailActivity;
import com.mahavira.partnersms.inventory.presentation.returnrequest.ReturnRequestListActivity;

import org.parceler.Parcels;

import static com.mahavira.partnersms.inventory.presentation.requestdetail.ReturnRequestDetailActivity.REQUEST_EXTRA;

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

    @Override
    public void goToReturnRequestList(Context context) {
        Intent intent = new Intent(context, ReturnRequestListActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void goToReturnRequestDetail(Context context, ReturnRequest request) {
        Intent intent = new Intent(context, ReturnRequestDetailActivity.class);
        intent.putExtra(REQUEST_EXTRA, Parcels.wrap(request));
        context.startActivity(intent);
    }
}
