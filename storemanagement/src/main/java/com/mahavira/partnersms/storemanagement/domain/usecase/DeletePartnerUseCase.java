package com.mahavira.partnersms.storemanagement.domain.usecase;

import com.mahavira.partnersms.base.core.CompletableUseCase;
import com.mahavira.partnersms.storemanagement.domain.entitiy.Partner;
import com.mahavira.partnersms.storemanagement.domain.repo.PartnerRepository;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Created by norman on 15/07/18.
 *
 */

public class DeletePartnerUseCase implements CompletableUseCase<Partner> {

    private PartnerRepository mRepository;

    @Inject
    public DeletePartnerUseCase(PartnerRepository repository) {
        mRepository = repository;
    }

    @Override
    public Completable execute(Partner param) {
        return mRepository.deletePartner(param);
    }
}
