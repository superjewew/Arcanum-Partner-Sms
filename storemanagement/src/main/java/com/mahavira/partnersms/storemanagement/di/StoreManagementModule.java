package com.mahavira.partnersms.storemanagement.di;

import com.google.firebase.firestore.FirebaseFirestore;
import com.mahavira.partnersms.storemanagement.data.PartnerRepositoryImpl;
import com.mahavira.partnersms.storemanagement.domain.repo.PartnerRepository;
import com.mahavira.partnersms.storemanagement.domain.usecase.AddPartnerUseCase;
import com.mahavira.partnersms.storemanagement.domain.usecase.DeletePartnerUseCase;
import com.mahavira.partnersms.storemanagement.domain.usecase.GetPartnersUseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by norman on 14/07/18.
 *
 */

@Module(includes = {StoreManagementBuilderModule.class})
public class StoreManagementModule {

    @Provides
    @Singleton
    PartnerRepository providePartnerRepository(FirebaseFirestore firebaseFirestore) {
        return new PartnerRepositoryImpl(firebaseFirestore);
    }

    @Provides
    AddPartnerUseCase provideAddPartnerUseCase(PartnerRepository repository) {
        return new AddPartnerUseCase(repository);
    }

    @Provides
    DeletePartnerUseCase provideDeletePartnerUseCase(PartnerRepository repository) {
        return new DeletePartnerUseCase(repository);
    }
    @Provides
    GetPartnersUseCase provideGetPartnerUseCase(PartnerRepository repository) {
        return new GetPartnersUseCase(repository);
    }

}
