package com.mahavira.partnersms.di;

import android.app.Application;


import com.mahavira.partnersms.BaseApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import di.BaseModule;

/**
 * Created by bobbi on 13/03/18.
 */

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityBuilderModule.class,
        BaseModule.class
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
