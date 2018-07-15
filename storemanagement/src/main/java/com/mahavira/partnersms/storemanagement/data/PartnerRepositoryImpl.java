package com.mahavira.partnersms.storemanagement.data;

import com.mahavira.partnersms.storemanagement.domain.entitiy.Partner;
import com.mahavira.partnersms.storemanagement.domain.repo.PartnerRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by norman on 15/07/18.
 */

public class PartnerRepositoryImpl implements PartnerRepository {

    @Override
    public Completable addPartner(Partner partner) {
        return null;
    }

    @Override
    public Single<List<Partner>> getPartner() {
        return null;
    }

    @Override
    public Completable deletePartner(Partner partner) {
        return null;
    }
}
