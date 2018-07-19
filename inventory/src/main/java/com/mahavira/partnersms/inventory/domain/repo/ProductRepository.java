package com.mahavira.partnersms.inventory.domain.repo;

import com.mahavira.partnersms.inventory.domain.entity.Boardgame;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by norman on 17/07/18.
 *
 */

public interface ProductRepository {

    Single<List<Boardgame>> getProducts();
    Completable addProduct(Boardgame product);
    Completable deleteProduct(Boardgame product);
    Single<Boardgame> getProductByName(String name);
    Completable updateProduct(Boardgame product);
    Completable updateProducts(List<Boardgame> param);
}
