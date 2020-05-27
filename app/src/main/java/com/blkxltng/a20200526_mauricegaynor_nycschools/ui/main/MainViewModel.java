package com.blkxltng.a20200526_mauricegaynor_nycschools.ui.main;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.blkxltng.a20200526_mauricegaynor_nycschools.dagger.network.DaggerSchoolApiFactory;
import com.blkxltng.a20200526_mauricegaynor_nycschools.models.SchoolInfo;
import com.blkxltng.a20200526_mauricegaynor_nycschools.network.SchoolService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
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

    private void loadSchools() {
        Observable<List<SchoolInfo>> schools = schoolService.getSchools();
        progressVisibility.postValue(View.VISIBLE);

        schools
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<List<SchoolInfo>>() {
                    @Override
                    public void onNext(@NonNull List<SchoolInfo> schoolInfos) {
                        Timber.d("List of schools: %s", schoolInfos);
                        for(int i = 0; i < schoolInfos.size(); i++) {
                            Timber.d("School DBN: " + schoolInfos.get(i).dbn + "\nSchool Name: " + schoolInfos.get(i).school_name);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        progressVisibility.postValue(View.GONE);
                    }
                });

//                        ({ response ->
//                        progressVisibility.postValue(View.GONE)
//                        // Use list of games to make list of gameViewModels
//                        val gameVMList = mutableListOf<GameViewModel>()
//        for(game in response.results) {
//            gameVMList.add(GameViewModel(this).apply { this.game.value = game })
//        }
//        gameList.value = gameVMList
//            }, { error ->
//            if(error.message.equals("HTTP 404 Not Found")) {
//                errorCode.postValue(RawgErrorCode.NOT_FOUND)
//            } else {
//                errorCode.postValue(RawgErrorCode.ERROR_LIST)
//            }
//            progressVisibility.postValue(View.GONE)
//            Timber.d(error)
//        })
    }
}
