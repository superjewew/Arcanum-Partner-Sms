package com.mahavira.partnersms.storemanagement.di;

import android.arch.lifecycle.ViewModel;

import com.mahavira.partnersms.base.di.ViewModelKey;
import com.mahavira.partnersms.storemanagement.presentation.feature.addpartner.AddPartnerActivity;
import com.mahavira.partnersms.storemanagement.presentation.feature.addpartner.AddPartnerViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by norman on 14/07/18.
 *
 */

@Module
public abstract class StoreManagementBuilderModule {

    @ContributesAndroidInjector
    abstract AddPartnerActivity bindAddPartnerActivity();

    @Binds
    @IntoMap
    @ViewModelKey(AddPartnerViewModel.class)
    abstract ViewModel bindAddPartnerViewModel(AddPartnerViewModel addPartnerViewModel);

}
