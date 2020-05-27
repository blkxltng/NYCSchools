package com.blkxltng.a20200526_mauricegaynor_nycschools.network;

import com.blkxltng.a20200526_mauricegaynor_nycschools.models.SchoolInfo;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface SchoolApi {

    @GET("s3k6-pzi2.json")
    public Observable<List<SchoolInfo>> getSchools();

    @GET("f9bf-2cp4.json")
    public Observable<List<SchoolService>> getScores();
}
