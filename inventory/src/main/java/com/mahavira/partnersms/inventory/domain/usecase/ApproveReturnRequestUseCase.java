package com.mahavira.partnersms.inventory.domain.usecase;

import com.mahavira.partnersms.base.core.CompletableUseCase;
import com.mahavira.partnersms.inventory.domain.entity.ReturnRequest;
import com.mahavira.partnersms.inventory.domain.repo.ProductRepository;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Created by norman on 09/08/18.
 */

public class ApproveReturnRequestUseCase implements CompletableUseCase<ReturnRequest> {

    ProductRepository mRepository;

    @Inject
    ApproveReturnRequestUseCase(ProductRepository repository) {
        mRepository = repository;
    }

    @Override
    public Completable execute(ReturnRequest param) throws Exception {
        return mRepository.getReturnRequest(param.getProductName(), param.getFrom())
                .flatMapCompletable(request -> mRepository.deleteReturnRequest(request));
    }
}
