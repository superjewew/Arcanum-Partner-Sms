package com.mahavira.partnersms.storemanagement.data;

import android.support.annotation.NonNull;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mahavira.partnersms.storemanagement.domain.entitiy.Partner;
import com.mahavira.partnersms.storemanagement.domain.repo.PartnerRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by norman on 15/07/18.
 *
 */

public class PartnerRepositoryImpl implements PartnerRepository {

    private final String PARTNER_COLLECTION = "partner";
    private FirebaseFirestore mInstance;

    @Inject
    public PartnerRepositoryImpl(FirebaseFirestore instance) {
        mInstance = instance;
    }

    @Override
    public Completable addPartner(Partner partner) {
        return setValue(mInstance.collection(PARTNER_COLLECTION).document(partner.getUsername()), partner);
    }

    @Override
    public Single<List<Partner>> getPartner() {
        return getValue(mInstance.collection(PARTNER_COLLECTION), Partner.class).toSingle();
    }

    @Override
    public Completable deletePartner(Partner partner) {
        return deleteValue(mInstance.collection(PARTNER_COLLECTION).document(partner.getUsername()));
    }

    @Override
    public Completable updatePartner(Partner partner) {
        return setValue(mInstance.collection(PARTNER_COLLECTION).document(partner.getUsername()), partner);
    }

    @NonNull
    private Completable setValue(@NonNull final DocumentReference ref, final Object value) {
        return Completable.create(
                e -> ref.set(value)
                        .addOnSuccessListener(documentReference -> e.onComplete())
                        .addOnFailureListener(e::onError));
    }

    @NonNull
    private <T> Maybe<List<T>> getValue(@NonNull final CollectionReference ref, Class<T> clazz) {
        return Maybe.create(
                e -> ref.get()
                        .addOnCompleteListener(task -> e.onSuccess(task.getResult().toObjects(clazz)))
                        .addOnFailureListener(e::onError));
    }

    @NonNull
    private Completable deleteValue(@NonNull final DocumentReference ref) {
        return Completable.create(
                e -> ref.delete()
                        .addOnSuccessListener(documentReference -> e.onComplete())
                        .addOnFailureListener(e::onError));
    }

}
