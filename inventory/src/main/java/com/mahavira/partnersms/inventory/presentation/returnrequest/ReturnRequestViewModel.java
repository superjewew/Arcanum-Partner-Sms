package com.mahavira.partnersms.inventory.presentation.returnrequest;

import com.mahavira.partnersms.base.presentation.BaseViewModel;
import com.mahavira.partnersms.inventory.domain.usecase.GetReturnRequestsUseCase;

import javax.inject.Inject;

/**
 * Created by norman on 07/08/18.
 */

public class ReturnRequestViewModel extends BaseViewModel {

    private GetReturnRequestsUseCase mGetReturnRequestUseCase;

    @Inject
    public ReturnRequestViewModel(GetReturnRequestsUseCase getReturnRequestsUseCase) {
        mGetReturnRequestUseCase = getReturnRequestsUseCase;
    }
}
