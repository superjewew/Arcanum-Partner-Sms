package com.mahavira.partnersms.loan.domain.usecase;

import com.mahavira.partnersms.base.core.CompletableUseCase;
import com.mahavira.partnersms.base.entity.ReturnRequest;
import com.mahavira.partnersms.inventory.domain.repo.ProductRepository;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Created by norman on 10/08/18.
 */

public class RejectReturnRequestUseCase implements CompletableUseCase<ReturnRequest> {

    private ProductRepository mRepository;

    @Inject
    public RejectReturnRequestUseCase(ProductRepository repository) {
        mRepository = repository;
    }

    @Override
    public Completable execute(ReturnRequest param) throws Exception {
        return mRepository.deleteReturnRequest(param);
    }
}
