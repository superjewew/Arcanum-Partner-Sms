package com.mahavira.partnersms.di;

import android.app.Application;
import android.content.Context;

import com.mahavira.partnersms.login.LoginRouter;
import com.mahavira.partnersms.router.LoginRouterImpl;

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
    LoginRouter provideLoginRouter() {
        return new LoginRouterImpl();
    }
}
