package com.mahavira.partnersms.di;

import android.arch.lifecycle.ViewModel;


import com.mahavira.partnersms.MainActivity;
import com.mahavira.partnersms.MainViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;
import com.mahavira.partnersms.base.di.ViewModelKey;

/**
 * Created by norman on 09/07/18.
 */

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel provideViewModel(MainViewModel viewModel);
}
