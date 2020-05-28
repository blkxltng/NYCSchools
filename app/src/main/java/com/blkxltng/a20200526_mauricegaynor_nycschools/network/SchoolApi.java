package com.blkxltng.a20200526_mauricegaynor_nycschools.network;

import com.blkxltng.a20200526_mauricegaynor_nycschools.models.SatScores;
import com.blkxltng.a20200526_mauricegaynor_nycschools.models.SchoolInfo;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SchoolApi {

    @GET("s3k6-pzi2.json")
    Observable<List<SchoolInfo>> getSchools();

    @GET("f9bf-2cp4.json")
    Observable<List<SatScores>> getScores(@Query("dbn") String dbn);
}
