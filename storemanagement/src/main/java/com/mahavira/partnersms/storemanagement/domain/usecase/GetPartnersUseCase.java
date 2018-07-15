package com.mahavira.partnersms.storemanagement.domain.usecase;

import com.mahavira.partnersms.base.core.BaseUseCase;
import com.mahavira.partnersms.storemanagement.domain.entitiy.Partner;
import com.mahavira.partnersms.storemanagement.domain.repo.PartnerRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by norman on 15/07/18.
 *
 */

public class GetPartnersUseCase implements BaseUseCase<List<Partner>> {

    private PartnerRepository mRepository;

    @Inject
    GetPartnersUseCase(PartnerRepository repository) {
        mRepository = repository;
    }

    @Override
    public Single<List<Partner>> execute() {
        return mRepository.getPartner();
    }
}
