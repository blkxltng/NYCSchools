package com.blkxltng.a20200526_mauricegaynor_nycschools.dagger.network;

import com.blkxltng.a20200526_mauricegaynor_nycschools.network.SchoolApi;
import com.blkxltng.a20200526_mauricegaynor_nycschools.network.SchoolService;
import com.blkxltng.a20200526_mauricegaynor_nycschools.ui.main.MainViewModel;

import javax.inject.Singleton;

import dagger.Component;

// The providers and injector we'll need to make dagger work properly
@Singleton
@Component(modules = { SchoolModule.class })
public interface SchoolApiFactory {
    SchoolApi providesSchoolApi();
    SchoolService providesSchoolService();
    void inject(MainViewModel mainViewModel);
}
