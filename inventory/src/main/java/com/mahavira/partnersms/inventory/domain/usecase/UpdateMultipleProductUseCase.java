package com.mahavira.partnersms.inventory.domain.usecase;

import com.mahavira.partnersms.base.core.CompletableUseCase;
import com.mahavira.partnersms.base.entity.Boardgame;
import com.mahavira.partnersms.inventory.domain.repo.ProductRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Created by norman on 18/07/18.
 */

public class UpdateMultipleProductUseCase implements CompletableUseCase<List<Boardgame>> {

    private ProductRepository mRepository;

    @Inject
    public UpdateMultipleProductUseCase(ProductRepository repository) {
        mRepository = repository;
    }

    @Override
    public Completable execute(List<Boardgame> param) throws Exception {
        return mRepository.updateProducts(param);
    }
}
