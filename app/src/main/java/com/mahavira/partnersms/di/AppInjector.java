package com.mahavira.partnersms.di;


import com.mahavira.partnersms.BaseApplication;

public class AppInjector {

    private AppInjector() {
    }

    public static void init(BaseApplication application) {
        DaggerAppComponent
                .builder()
                .application(application)
                .build()
                .inject(application);

    }
}