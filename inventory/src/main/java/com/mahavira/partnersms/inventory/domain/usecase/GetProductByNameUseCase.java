package com.mahavira.partnersms.inventory.domain.usecase;

import com.mahavira.partnersms.base.core.BaseUseCaseWithParam;
import com.mahavira.partnersms.base.entity.Boardgame;
import com.mahavira.partnersms.inventory.domain.repo.ProductRepository;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by norman on 17/07/18.
 *
 */

public class GetProductByNameUseCase implements BaseUseCaseWithParam<String, Boardgame> {

    private ProductRepository mRepository;

    @Inject
    public GetProductByNameUseCase(ProductRepository repository) {
        mRepository = repository;
    }

    @Override
    public Single<Boardgame> execute(String param) {
        return mRepository.getProductByName(param);
    }
}
