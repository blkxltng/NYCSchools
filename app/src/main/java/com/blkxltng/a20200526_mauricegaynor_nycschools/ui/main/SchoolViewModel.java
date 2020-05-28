package com.blkxltng.a20200526_mauricegaynor_nycschools.ui.main;

import com.blkxltng.a20200526_mauricegaynor_nycschools.models.SchoolInfo;

public class SchoolViewModel {

    public SchoolInfo info;
    public MainViewModel mainViewModel;

    public SchoolViewModel(SchoolInfo info, MainViewModel mainViewModel) {
        this.info = info;
        this.mainViewModel = mainViewModel;
    }

    public void schoolClicked() {
        mainViewModel.clickedSchool.postValue(info);
    }
}
