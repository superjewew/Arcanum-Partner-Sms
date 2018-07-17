package com.mahavira.partnersms.storemanagement.domain.repo;

import com.mahavira.partnersms.storemanagement.domain.entitiy.Partner;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by norman on 15/07/18.
 *
 */

public interface PartnerRepository {
    Completable addPartner(Partner partner);
    Single<List<Partner>> getPartner();
    Completable deletePartner(Partner partner);
}
