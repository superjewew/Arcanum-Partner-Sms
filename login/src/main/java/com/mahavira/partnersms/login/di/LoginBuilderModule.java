package com.mahavira.partnersms.login.di;

import android.arch.lifecycle.ViewModel;

import com.mahavira.partnersms.login.presentation.LoginActivity;
import com.mahavira.partnersms.login.presentation.LoginViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;
import di.ViewModelKey;

/**
 * Created by norman on 13/07/18.
 *
 */

@Module
abstract class LoginBuilderModule {

    @ContributesAndroidInjector
    abstract LoginActivity bindLoginActivity();

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindLoginViewModel(LoginViewModel loginViewModel);
}
