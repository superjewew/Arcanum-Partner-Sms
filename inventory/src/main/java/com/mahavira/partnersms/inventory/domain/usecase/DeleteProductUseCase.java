package com.mahavira.partnersms.inventory.domain.usecase;

import com.mahavira.partnersms.base.core.CompletableUseCase;
import com.mahavira.partnersms.base.entity.Boardgame;
import com.mahavira.partnersms.inventory.domain.repo.ProductRepository;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Created by norman on 17/07/18.
 *
 */

public class DeleteProductUseCase implements CompletableUseCase<Boardgame> {

    private ProductRepository mRepository;

    @Inject
    DeleteProductUseCase(ProductRepository repository) {
        mRepository = repository;
    }

    @Override
    public Completable execute(Boardgame param) {
        return mRepository.deleteProduct(param);
    }
}
