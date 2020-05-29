package com.blkxltng.a20200526_mauricegaynor_nycschools;

import android.app.Application;

import timber.log.Timber;

public class SchoolsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Used so that we can use Timber freely throughout the app
        Timber.plant(new Timber.DebugTree());
    }
}
