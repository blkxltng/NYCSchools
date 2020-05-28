package com.blkxltng.a20200526_mauricegaynor_nycschools.ui.main;

import com.airbnb.epoxy.TypedEpoxyController;
import com.blkxltng.a20200526_mauricegaynor_nycschools.ItemSchoolCardBindingModel_;

import java.util.List;

public class MainEpoxyController extends TypedEpoxyController<List<SchoolViewModel>> {

    @Override
    protected void buildModels(List<SchoolViewModel> data) {
        for(SchoolViewModel item : data) {
            new ItemSchoolCardBindingModel_()
                    .id(item.info.dbn)
                    .schoolViewModel(item)
                    .addTo(this);
        }
    }
}
