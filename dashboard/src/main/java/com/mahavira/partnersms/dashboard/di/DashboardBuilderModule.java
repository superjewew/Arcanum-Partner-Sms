package com.mahavira.partnersms.dashboard.di;

import android.arch.lifecycle.ViewModel;

import com.mahavira.partnersms.base.di.ViewModelKey;
import com.mahavira.partnersms.dashboard.presentation.DashboardActivity;
import com.mahavira.partnersms.dashboard.presentation.DashboardViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by norman on 13/07/18.
 *
 */

@Module
public abstract class DashboardBuilderModule {

    @ContributesAndroidInjector
    abstract DashboardActivity bindDashboardActivity();

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel.class)
    abstract ViewModel bindDashboardViewModel(DashboardViewModel dashboardViewModel);
}
