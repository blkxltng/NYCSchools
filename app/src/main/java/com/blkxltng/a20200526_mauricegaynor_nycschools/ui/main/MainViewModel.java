package com.blkxltng.a20200526_mauricegaynor_nycschools.ui.main;

import android.util.Pair;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.blkxltng.a20200526_mauricegaynor_nycschools.dagger.network.DaggerSchoolApiFactory;
import com.blkxltng.a20200526_mauricegaynor_nycschools.models.SatScores;
import com.blkxltng.a20200526_mauricegaynor_nycschools.models.SchoolInfo;
import com.blkxltng.a20200526_mauricegaynor_nycschools.network.SchoolService;
import com.blkxltng.a20200526_mauricegaynor_nycschools.utils.LiveEvent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import timber.log.Timber;

public class MainViewModel extends ViewModel {

    @Inject
    public SchoolService schoolService;

    public MainViewModel() {
        DaggerSchoolApiFactory.create().inject(this);
        loadSchools();
    }

    public MutableLiveData<Integer> progressVisibility = new MutableLiveData<>(View.GONE); // Used to set progressBar visibility
    public MutableLiveData<List<SchoolViewModel>> schoolInfoList = new MutableLiveData<>();
    public LiveEvent<SchoolInfo> clickedSchool = new LiveEvent<>();
    public LiveEvent<Pair<SchoolInfo, SatScores>> schoolScores = new LiveEvent<>();

    private void loadSchools() {
        Observable<List<SchoolInfo>> schools = schoolService.getSchools();
        progressVisibility.postValue(View.VISIBLE);

        schools
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<List<SchoolInfo>>() {
                    @Override
                    public void onNext(@NonNull List<SchoolInfo> schoolInfos) {
                        List<SchoolViewModel> vmList = new ArrayList<>();
                        for(SchoolInfo info : schoolInfos) {
                            Timber.d("School DBN: " + info.dbn + "\nSchool Name: " + info.school_name);
                            vmList.add(new SchoolViewModel(info, MainViewModel.this));
                        }
                        schoolInfoList.postValue(vmList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        progressVisibility.postValue(View.GONE);
                    }

                    @Override
                    public void onComplete() {
                        progressVisibility.postValue(View.GONE);
                    }
                });
    }

    public void getScores(String dbn) {
        Observable<List<SatScores>> scores = schoolService.getScores(dbn);
        progressVisibility.postValue(View.VISIBLE);

        scores
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<List<SatScores>>() {
                    @Override
                    public void onNext(@NonNull List<SatScores> scores) {
                        Timber.d("School Info: " + clickedSchool.getValue().getDbn());
                        if(scores.size() > 0) {
                            Timber.d("Scores are: " + scores.get(0));
                            schoolScores.postValue(new Pair(clickedSchool.getValue(), scores.get(0)));
                        } else {
                            // No SAT scores could be found for this school
                            schoolScores.postValue(new Pair(clickedSchool.getValue(), null));
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.d(e);
                        progressVisibility.postValue(View.GONE);
                    }

                    @Override
                    public void onComplete() {
                        progressVisibility.postValue(View.GONE);
                    }
                });
    }
}

enum SchoolErrorCode {
    NOT_FOUND, NO_CONNECTION, ERROR_SCHOOL, ERROR_SCORES
}
