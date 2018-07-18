package com.mahavira.partnersms.di;

import android.app.Application;
import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mahavira.partnersms.dashboard.presentation.DashboardRouter;
import com.mahavira.partnersms.inventory.presentation.InventoryRouter;
import com.mahavira.partnersms.login.LoginRouter;
import com.mahavira.partnersms.router.DashboardRouterImpl;
import com.mahavira.partnersms.router.InventoryRouterImpl;
import com.mahavira.partnersms.router.LoginRouterImpl;
import com.mahavira.partnersms.router.StoreManagementRouterImpl;
import com.mahavira.partnersms.storemanagement.presentation.StoreManagementRouter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by norman on 09/07/18.
 *
 */

@Module
class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    FirebaseAuth provideFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    @Provides
    LoginRouter provideLoginRouter() {
        return new LoginRouterImpl();
    }

    @Provides
    DashboardRouter provideDashboardRouter() {
        return new DashboardRouterImpl();
    }

    @Provides
    StoreManagementRouter provideStoreManagementRouter() {
        return new StoreManagementRouterImpl();
    }

    @Provides
    InventoryRouter provideInventoryRouter() {
        return new InventoryRouterImpl();
    }

    @Singleton
    @Provides
    FirebaseFirestore provideFirebaseFirestore() {
        return FirebaseFirestore.getInstance();
    }
}
