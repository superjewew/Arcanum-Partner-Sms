package com.mahavira.partnersms.inventory.domain.usecase;

import com.mahavira.partnersms.base.core.BaseUseCase;
import com.mahavira.partnersms.base.entity.Boardgame;
import com.mahavira.partnersms.inventory.domain.repo.ProductRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by norman on 17/07/18.
 *
 */

public class GetProductsUseCase implements BaseUseCase<List<Boardgame>> {

    private ProductRepository mRepository;

    @Inject
    public GetProductsUseCase(ProductRepository repository) {
        mRepository = repository;
    }

    @Override
    public Single<List<Boardgame>> execute() {
        return mRepository.getProducts();
    }
}
