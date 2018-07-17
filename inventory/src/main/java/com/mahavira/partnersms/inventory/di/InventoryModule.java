package com.mahavira.partnersms.inventory.di;

import com.mahavira.partnersms.inventory.data.ProductRepoImpl;
import com.mahavira.partnersms.inventory.domain.repo.ProductRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by norman on 17/07/18.
 *
 */

@Module(includes = {InventoryBuilderModule.class})
public class InventoryModule {

    @Provides
    @Singleton
    ProductRepository provideProductRepo() {
        return new ProductRepoImpl();
    }
}
