package com.blkxltng.a20200526_mauricegaynor_nycschools.dagger.network;

import com.blkxltng.a20200526_mauricegaynor_nycschools.network.SchoolApi;

import dagger.Module;
import dagger.Provides;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import kotlin.jvm.JvmStatic;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import static com.blkxltng.a20200526_mauricegaynor_nycschools.utils.ConstantUtils.NYC_API;

// The module we'll use to tell dagger how to create and provide the retrofit object we'll use
@Module
public class SchoolModule {
    @Provides
    @JvmStatic
    public SchoolApi schoolApi() {
        return new Retrofit.Builder().baseUrl(NYC_API)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build().create(SchoolApi.class);
    }
}
