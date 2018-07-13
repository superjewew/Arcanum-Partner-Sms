package com.mahavira.partnersms.login.di;

import com.google.firebase.auth.FirebaseAuth;
import com.mahavira.partnersms.login.data.LoginRepoImpl;
import com.mahavira.partnersms.login.domain.repo.LoginRepository;
import com.mahavira.partnersms.login.domain.usecase.LoginUseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by norman on 13/07/18.
 *
 */

@Module(includes = {LoginBuilderModule.class})
public class LoginModule {

    @Provides
    @Singleton
    LoginRepository provideLoginRepository(FirebaseAuth auth) {
        return new LoginRepoImpl(auth);
    }

    @Provides
    @Singleton
    LoginUseCase provideLoginUseCase(LoginRepository repository) {
        return new LoginUseCase(repository);
    }
}
