package com.mahavira.partnersms.inventory.data;

import com.mahavira.partnersms.inventory.domain.entity.Boardgame;
import com.mahavira.partnersms.inventory.domain.repo.ProductRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by norman on 17/07/18.
 */

public class ProductRepoImpl implements ProductRepository {
    @Override
    public Single<List<Boardgame>> getProducts() {
        return null;
    }

    @Override
    public Completable addProduct(Boardgame product) {
        return null;
    }

    @Override
    public Completable deleteProduct(Boardgame product) {
        return null;
    }

    @Override
    public Single<Boardgame> getProductByName(String name) {
        return null;
    }

    @Override
    public Completable updateProduct(Boardgame product) {
        return null;
    }
}
