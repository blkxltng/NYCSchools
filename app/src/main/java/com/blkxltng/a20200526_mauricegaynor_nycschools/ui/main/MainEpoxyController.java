package com.blkxltng.a20200526_mauricegaynor_nycschools.ui.main;

import com.airbnb.epoxy.TypedEpoxyController;
import com.blkxltng.a20200526_mauricegaynor_nycschools.ItemSchoolCardBindingModel_;

import java.util.List;

public class MainEpoxyController extends TypedEpoxyController<List<SchoolViewModel>> {

    @Override
    protected void buildModels(List<SchoolViewModel> data) {
        for(SchoolViewModel item : data) {
            // Assign each school to a card view and add it to the recyclerView
            new ItemSchoolCardBindingModel_()
                    .id(item.info.getDbn())
                    .schoolViewModel(item)
                    .addTo(this);
        }
    }
}
