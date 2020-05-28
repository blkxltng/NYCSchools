package com.blkxltng.a20200526_mauricegaynor_nycschools.network;

import com.blkxltng.a20200526_mauricegaynor_nycschools.models.SatScores;
import com.blkxltng.a20200526_mauricegaynor_nycschools.models.SchoolInfo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class SchoolService {

    private SchoolApi schoolApi;

    @Inject
    public SchoolService (SchoolApi schoolApi) {
        this.schoolApi = schoolApi;
    }

    public Observable<List<SchoolInfo>> getSchools() {
        return schoolApi.getSchools();
    }

    public Observable<List<SatScores>> getScores(String dbn){
        return schoolApi.getScores(dbn);
    }
}
