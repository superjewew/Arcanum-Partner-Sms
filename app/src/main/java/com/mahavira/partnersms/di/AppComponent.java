package com.mahavira.partnersms.di;

import android.app.Application;


import com.mahavira.partnersms.BaseApplication;
import com.mahavira.partnersms.dashboard.di.DashboardModule;
import com.mahavira.partnersms.inventory.di.InventoryModule;
import com.mahavira.partnersms.loan.di.LoanModule;
import com.mahavira.partnersms.login.di.LoginModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import com.mahavira.partnersms.base.di.BaseModule;
import com.mahavira.partnersms.storemanagement.di.StoreManagementModule;

/**
 * Created by bobbi on 13/03/18.
 *
 */

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityBuilderModule.class,
        BaseModule.class,
        LoginModule.class,
        DashboardModule.class,
        StoreManagementModule.class,
        InventoryModule.class,
        LoanModule.class
})
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(BaseApplication application);
}
