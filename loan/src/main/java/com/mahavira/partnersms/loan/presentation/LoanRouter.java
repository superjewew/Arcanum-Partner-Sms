package com.mahavira.partnersms.loan.presentation;

import android.content.Context;

import com.mahavira.partnersms.base.entity.ReturnRequest;

/**
 * Created by norman on 10/08/18.
 */

public interface LoanRouter {
    void goToReturnRequestList(Context context);
    void goToReturnRequestDetail(Context context, ReturnRequest request);
}
