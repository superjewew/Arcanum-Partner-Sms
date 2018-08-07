package com.mahavira.partnersms.inventory.data;

import android.support.annotation.NonNull;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;
import com.mahavira.partnersms.inventory.domain.entity.Boardgame;
import com.mahavira.partnersms.inventory.domain.entity.ReturnRequest;
import com.mahavira.partnersms.inventory.domain.repo.ProductRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by norman on 17/07/18.
 *
 */

public class ProductRepoImpl implements ProductRepository {

    private static final String REQUEST_COLLECTION = "return_request";

    private static final String PRODUCT_COLLECTION = "products";

    private FirebaseFirestore mInstance;

    @Inject
    public ProductRepoImpl(FirebaseFirestore instance) {
        mInstance = instance;
    }

    @Override
    public Single<List<Boardgame>> getProducts() {
        return getValue(mInstance.collection(PRODUCT_COLLECTION), Boardgame.class).toSingle();
    }

    @Override
    public Completable addProduct(Boardgame product) {
        return setValue(mInstance.collection(PRODUCT_COLLECTION).document(product.getName()), product);
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
        return setValue(mInstance.collection(PRODUCT_COLLECTION).document(product.getName()), product);
    }

    @Override
    public Completable updateProducts(List<Boardgame> param) {
        WriteBatch batch = mInstance.batch();
        for (Boardgame product : param) {
            DocumentReference ref = mInstance.collection(PRODUCT_COLLECTION).document(product.getName());
            batch.set(ref, product);
        }
        return setValue(batch);
    }

    @Override
    public Single<List<ReturnRequest>> getReturnRequests() {
        return getValue(mInstance.collection(REQUEST_COLLECTION), ReturnRequest.class).toSingle();
    }

    @NonNull
    private Completable setValue(@NonNull final DocumentReference ref, final Object value) {
        return Completable.create(
                e -> ref.set(value)
                        .addOnSuccessListener(documentReference -> e.onComplete())
                        .addOnFailureListener(e::onError));
    }

    @NonNull
    private Completable setValue(@NonNull WriteBatch batch) {
        return Completable.create(e -> batch.commit().addOnCompleteListener(task -> e.onComplete()));
    }

    @NonNull
    private <T> Maybe<List<T>> getValue(@NonNull final CollectionReference ref, Class<T> clazz) {
        return Maybe.create(
                e -> ref.get()
                        .addOnCompleteListener(task -> e.onSuccess(task.getResult().toObjects(clazz)))
                        .addOnFailureListener(e::onError));
    }
}
