package com.mahavira.partnersms.inventory.data;

import android.support.annotation.NonNull;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mahavira.partnersms.inventory.domain.entity.Boardgame;
import com.mahavira.partnersms.inventory.domain.repo.ProductRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by norman on 17/07/18.
 *
 */

public class ProductRepoImpl implements ProductRepository {

    private FirebaseFirestore mInstance;

    @Inject
    public ProductRepoImpl(FirebaseFirestore instance) {
        mInstance = instance;
    }

    @Override
    public Single<List<Boardgame>> getProducts() {
        return null;
    }

    @Override
    public Completable addProduct(Boardgame product) {
        return setValue(mInstance.collection("products").document(product.getName()), product);
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

    @NonNull
    private Completable setValue(@NonNull final DocumentReference ref, final Object value) {
        return Completable.create(
                e -> ref.set(value)
                        .addOnSuccessListener(documentReference -> e.onComplete())
                        .addOnFailureListener(e::onError));
    }
}
