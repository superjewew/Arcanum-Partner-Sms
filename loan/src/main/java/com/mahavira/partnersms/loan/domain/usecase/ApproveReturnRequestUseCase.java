package com.mahavira.partnersms.loan.domain.usecase;

import com.mahavira.partnersms.base.core.CompletableUseCase;
import com.mahavira.partnersms.base.entity.ReturnRequest;
import com.mahavira.partnersms.inventory.domain.repo.ProductRepository;
import com.mahavira.partnersms.storemanagement.domain.entitiy.Partner;
import com.mahavira.partnersms.storemanagement.domain.repo.PartnerRepository;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Created by norman on 09/08/18.
 */

public class ApproveReturnRequestUseCase implements CompletableUseCase<ReturnRequest> {

    private ProductRepository mRepository;

    private PartnerRepository mPartnerRepository;

    @Inject
    ApproveReturnRequestUseCase(ProductRepository repository, PartnerRepository partnerRepository) {
        mRepository = repository;
        mPartnerRepository = partnerRepository;
    }

    @Override
    public Completable execute(ReturnRequest param) throws Exception {
        return mRepository.getReturnRequest(param.getProductName(), param.getFrom())
                .flatMap(request -> mPartnerRepository.getPartnerByName(request.getFrom()))
                .map(partner -> removeBorrowedGames(partner, param.getProductName()))
                .flatMapCompletable(partner -> mPartnerRepository.updatePartner(partner))
                .andThen(mRepository.deleteReturnRequest(param));
    }

    Partner removeBorrowedGames(Partner partner, String game) {
        partner.getBorrowedGames().remove(game);
        return partner;
    }
}
