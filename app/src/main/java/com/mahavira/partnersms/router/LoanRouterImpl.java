package com.mahavira.partnersms.router;

import android.content.Context;
import android.content.Intent;

import com.mahavira.partnersms.base.entity.ReturnRequest;
import com.mahavira.partnersms.loan.presentation.LoanRouter;
import com.mahavira.partnersms.loan.presentation.requestdetail.ReturnRequestDetailActivity;
import com.mahavira.partnersms.loan.presentation.returnrequest.ReturnRequestListActivity;

import org.parceler.Parcels;

import static com.mahavira.partnersms.loan.presentation.requestdetail.ReturnRequestDetailActivity.REQUEST_EXTRA;

/**
 * Created by norman on 10/08/18.
 */

public class LoanRouterImpl implements LoanRouter {
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
