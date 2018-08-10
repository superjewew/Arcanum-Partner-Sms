package com.mahavira.partnersms.inventory.domain.usecase;

import com.mahavira.partnersms.base.core.BaseUseCase;
import com.mahavira.partnersms.base.entity.ReturnRequest;
import com.mahavira.partnersms.inventory.domain.repo.ProductRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by norman on 07/08/18.
 */

public class GetReturnRequestsUseCase implements BaseUseCase<List<ReturnRequest>> {

    ProductRepository mRepository;

    @Inject
    GetReturnRequestsUseCase(ProductRepository repository) {
        mRepository = repository;
    }

    @Override
    public Single<List<ReturnRequest>> execute() {
        return mRepository.getReturnRequests();
    }
}
