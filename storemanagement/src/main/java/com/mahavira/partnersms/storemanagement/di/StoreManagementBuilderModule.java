package com.mahavira.partnersms.storemanagement.di;

import android.arch.lifecycle.ViewModel;

import com.mahavira.partnersms.base.di.ViewModelKey;
import com.mahavira.partnersms.storemanagement.presentation.feature.addpartner.AddPartnerActivity;
import com.mahavira.partnersms.storemanagement.presentation.feature.addpartner.AddPartnerViewModel;
import com.mahavira.partnersms.storemanagement.presentation.feature.partnerdetail.PartnerDetailActivity;
import com.mahavira.partnersms.storemanagement.presentation.feature.partnerdetail.PartnerDetailViewModel;
import com.mahavira.partnersms.storemanagement.presentation.feature.partnerlist.PartnerListActivity;
import com.mahavira.partnersms.storemanagement.presentation.feature.partnerlist.PartnerListViewModel;

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

    @ContributesAndroidInjector
    abstract PartnerListActivity bindPartnerListActivity();

    @ContributesAndroidInjector
    abstract PartnerDetailActivity bindPartnerDetailActivity();

    @Binds
    @IntoMap
    @ViewModelKey(AddPartnerViewModel.class)
    abstract ViewModel bindAddPartnerViewModel(AddPartnerViewModel addPartnerViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PartnerListViewModel.class)
    abstract ViewModel bindPartnerListViewModel(PartnerListViewModel partnerListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PartnerDetailViewModel.class)
    abstract ViewModel bindPartnerDetailViewModel(PartnerDetailViewModel partnerDetailViewModel);

}
