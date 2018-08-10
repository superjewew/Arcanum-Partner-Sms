package com.mahavira.partnersms.loan.di;

import android.arch.lifecycle.ViewModel;

import com.mahavira.partnersms.base.di.ViewModelKey;
import com.mahavira.partnersms.loan.presentation.requestdetail.ReturnRequestDetailActivity;
import com.mahavira.partnersms.loan.presentation.requestdetail.ReturnRequestDetailViewModel;
import com.mahavira.partnersms.loan.presentation.returnrequest.ReturnRequestListActivity;
import com.mahavira.partnersms.loan.presentation.returnrequest.ReturnRequestViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by norman on 10/08/18.
 */

@Module
public abstract class LoanBuilderModule {

    @ContributesAndroidInjector
    abstract ReturnRequestListActivity bindReturnRequestListActivity();

    @ContributesAndroidInjector
    abstract ReturnRequestDetailActivity bindReturnRequestDetailActivity();

    @Binds
    @IntoMap
    @ViewModelKey(ReturnRequestViewModel.class)
    abstract ViewModel bindReturnRequestListViewModel(ReturnRequestViewModel returnRequestViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ReturnRequestDetailViewModel.class)
    abstract ViewModel bindReturnRequestDetailViewModel(ReturnRequestDetailViewModel returnRequestDetailViewModel);
}
