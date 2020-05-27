package com.blkxltng.a20200526_mauricegaynor_nycschools.ui.main;

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
import com.blkxltng.a20200526_mauricegaynor_nycschools.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {

    private FragmentMainBinding binding;
    private MainViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        binding.setMainViewModel(viewModel);
        binding.executePendingBindings();
    }
}
