package com.blkxltng.a20200526_mauricegaynor_nycschools.ui.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.blkxltng.a20200526_mauricegaynor_nycschools.R;
import com.blkxltng.a20200526_mauricegaynor_nycschools.databinding.FragmentDetailsBinding;
import com.blkxltng.a20200526_mauricegaynor_nycschools.models.SatScores;
import com.blkxltng.a20200526_mauricegaynor_nycschools.models.SchoolInfo;

public class DetailsFragment extends Fragment {

    private DetailsViewModel viewModel;
    private FragmentDetailsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(DetailsViewModel.class);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SchoolInfo info = DetailsFragmentArgs.fromBundle(getArguments()).getInfo();
        SatScores scores = DetailsFragmentArgs.fromBundle(getArguments()).getScores();

        binding.setSchoolInfo(info);
        binding.setSatScores(scores);


//        binding.setMainViewModel(viewModel);
        binding.executePendingBindings();
//        setupObservers();
    }
}
