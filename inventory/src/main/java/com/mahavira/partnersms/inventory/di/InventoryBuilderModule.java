package com.mahavira.partnersms.inventory.di;

import android.arch.lifecycle.ViewModel;

import com.mahavira.partnersms.base.di.ViewModelKey;
import com.mahavira.partnersms.inventory.presentation.addproduct.AddProductActivity;
import com.mahavira.partnersms.inventory.presentation.addproduct.AddProductViewModel;
import com.mahavira.partnersms.inventory.presentation.getproducts.ProductListActivity;
import com.mahavira.partnersms.inventory.presentation.getproducts.ProductListViewModel;

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

    @ContributesAndroidInjector
    abstract ProductListActivity bindProductListActivity();

    @Binds
    @IntoMap
    @ViewModelKey(AddProductViewModel.class)
    abstract ViewModel bindAddProductViewModel(AddProductViewModel addProductViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ProductListViewModel.class)
    abstract ViewModel bindProductListViewModel(ProductListViewModel productListViewModel);
}
