package com.mahavira.partnersms.inventory.di;

import android.arch.lifecycle.ViewModel;

import com.mahavira.partnersms.base.di.ViewModelKey;
import com.mahavira.partnersms.inventory.presentation.addproduct.AddProductActivity;
import com.mahavira.partnersms.inventory.presentation.addproduct.AddProductViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by norman on 17/07/18.
 *
 */

@Module
public abstract class InventoryBuilderModule {

    @ContributesAndroidInjector
    abstract AddProductActivity bindAddProductActivity();

    @Binds
    @IntoMap
    @ViewModelKey(AddProductViewModel.class)
    abstract ViewModel bindAddProductViewModel(AddProductViewModel addProductViewModel);
}
