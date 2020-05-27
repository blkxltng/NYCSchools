package com.blkxltng.a20200526_mauricegaynor_nycschools;

import android.app.Application;

import timber.log.Timber;

public class SchoolsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
